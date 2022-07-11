@file:OptIn(ExperimentalMaterialApi::class)

package com.tahakorkem.unigrade.ui.screen.lecturelist

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.tahakorkem.unigrade.FakeItems
import com.tahakorkem.unigrade.R
import com.tahakorkem.unigrade.data.Grade
import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.Term
import com.tahakorkem.unigrade.ui.NavDestinations
import com.tahakorkem.unigrade.ui.theme.UniGradeTheme
import com.tahakorkem.unigrade.util.toPrettyString

@Composable
fun LectureListScreen(
    navController: NavHostController,
    viewModel: LectureListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val fabShape = RoundedCornerShape(percent = 50)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        },
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(horizontal = 16.dp),
                cutoutShape = fabShape,
            ) {
                if (!uiState.loading) {
                    Text(
                        text = buildAnnotatedString {
                            pushStyle(style = SpanStyle(fontWeight = FontWeight.Medium))
                            append("GANO: ")
                            pop()
                            append(uiState.total!!.averageGrade.toPrettyString())
                        },
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onPrimary,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = buildAnnotatedString {
                            pushStyle(style = SpanStyle(fontWeight = FontWeight.Medium))
                            append("Toplam Kredi: ")
                            pop()
                            append(uiState.total!!.totalCredits.toPrettyString())
                        },
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onPrimary,
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.insert(
                        lecture = Lecture(
                            name = "Yeni Ders",
                            code = "BLM123",
                            grade = null,
                            credits = 5.0f,
                            gradingScheme = listOf()
                        ),
                        term = Term.FallTerm(year = Term.Year(beginning = 2020)),
                    )
                },
                shape = fabShape,
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        if (uiState.loading) {
            Loading()
        } else {
            LectureList(
                terms = uiState.total!!.terms,
                onLectureClick = { lecture ->
                    navController.navigate("${NavDestinations.LECTURE_DETAIL_ROUTE}/${lecture.code}") {
                    }
                },
            )
        }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LectureListScreenPreview() {
    UniGradeTheme {
        LectureList(
            terms = FakeItems.terms,
            onLectureClick = { lecture ->

            },
        )
    }
}

@Composable
private fun LectureItem(lecture: Lecture, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onBackground.copy(alpha = .12f)
        ),
        elevation = 0.dp,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = lecture.name,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = lecture.code,
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.onBackground.copy(alpha = .6f),
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(6.dp))
            Column(
                modifier = Modifier
                    .width(58.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = lecture.grade?.name ?: "—",
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(
                        id = lecture.grade?.localizedTextResId ?: R.string.pending
                    ),
                    style = MaterialTheme.typography.subtitle2.copy(fontSize = 8.sp),
                    color = MaterialTheme.colors.onBackground.copy(alpha = .6f),
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Column(
                modifier = Modifier.width(24.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = lecture.credits.toPrettyString(),
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(id = R.string.credits),
                    style = MaterialTheme.typography.subtitle2.copy(fontSize = 8.sp),
                    color = MaterialTheme.colors.onBackground.copy(alpha = .6f),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LectureItemPreview() {
    UniGradeTheme {
        LectureItem(
            lecture = Lecture(
                name = "Bilgisayar Bilimlerine Giriş",
                code = "BLM1011",
                grade = Grade.BB,
                credits = 3.5f,
                gradingScheme = listOf()
            ),
            onClick = {}
        )
    }
}

@Composable
private fun LectureList(
    terms: List<Term>,
    onLectureClick: (Lecture) -> Unit,
    modifier: Modifier = Modifier
) {
    var collapsedTerms by remember { mutableStateOf<Set<Term>>(emptySet()) }
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
//            start = 16.dp,
            top = 16.dp,
//            end = 16.dp,
            bottom = 56.dp
        )
    ) {
        terms.forEach { term ->
            val isExpanded = term !in collapsedTerms
            item {
                TermHeader(
                    term = term,
                    isExpanded = isExpanded,
                    modifier = Modifier
                        .toggleable(
                            value = isExpanded,
                            onValueChange = {
                                collapsedTerms =
                                    if (it) collapsedTerms - term
                                    else collapsedTerms + term
                            }
                        )
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
            if (isExpanded) {
                itemsIndexed(items = term.lectures) { index, lecture ->
                    LectureItemWithDot(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        lecture = lecture,
                        itemPosition = mutableSetOf<ItemPosition>().apply {
                            if (index == 0) add(ItemPosition.First)
                            if (index == term.lectures.lastIndex) add(ItemPosition.Last)
                        },
                        onLectureClick = onLectureClick
                    )
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 28.5.dp + 16.dp,
                                end = 16.dp,
                                bottom = 16.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                pushStyle(style = SpanStyle(fontWeight = FontWeight.Medium))
                                append("YANO: ")
                                pop()
                                append(term.averageGrade.toPrettyString())
                            },
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = buildAnnotatedString {
                                pushStyle(style = SpanStyle(fontWeight = FontWeight.Medium))
                                append("Dönem Kredi: ")
                                pop()
                                append(term.totalCredits.toPrettyString())
                            },
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TermHeader(term: Term, isExpanded: Boolean, modifier: Modifier = Modifier) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 0f else -90f,
        animationSpec = tween(durationMillis = 200)
    )
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Filled.ExpandMore,
            contentDescription = null,
            modifier = Modifier.rotate(rotationAngle)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.secondary,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(horizontal = 9.dp, vertical = 3.dp),
            text = stringResource(id = term.semesterNameResId, term.year),
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Medium
        )
    }
}

private enum class ItemPosition {
    First, Last,
}

@Composable
private fun LectureItemWithDot(
    lecture: Lecture,
    itemPosition: Set<ItemPosition>,
    onLectureClick: (Lecture) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(64.dp)
    ) {
        Spacer(modifier = Modifier.width(7.5.dp))
        val secondaryColor = MaterialTheme.colors.secondary
        Canvas(
            modifier = Modifier
                .width(9.dp)
                .fillMaxHeight()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawCircle(
                color = secondaryColor,
                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                radius = 4.5.dp.toPx()
            )
            drawLine(
                color = secondaryColor,
                start = Offset(
                    x = canvasWidth / 2,
                    y = if (ItemPosition.First in itemPosition) 3.dp.toPx() else 0f
                ),
                end = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                strokeWidth = 1.dp.toPx(),
            )
            if (ItemPosition.Last !in itemPosition) {
                drawLine(
                    color = secondaryColor,
                    start = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                    end = Offset(x = canvasWidth / 2, y = canvasHeight),
                    strokeWidth = 1.dp.toPx(),
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        LectureItem(
            modifier = Modifier.padding(vertical = 3.dp),
            lecture = lecture,
            onClick = { onLectureClick(lecture) }
        )
    }
}