@file:OptIn(ExperimentalMaterialApi::class)

package com.tahakorkem.unigrade.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tahakorkem.unigrade.data.Grade
import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.ui.theme.Shapes
import com.tahakorkem.unigrade.ui.theme.UniGradeTheme
import com.tahakorkem.unigrade.R
import com.tahakorkem.unigrade.util.toPrettyString

@Composable
fun ListScreen() {
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }
    LectureList(
        lectures = listOf(
            Lecture(
                name = "Bilgisayar Bilimlerine Giriş",
                code = "BLM1011",
                grade = Grade.BB,
                credits = 4.0f,
                gradingScheme = listOf()
            ),
            Lecture(
                name = "Matematik 1",
                code = "BLM1072",
                grade = Grade.FF,
                credits = 4.0f,
                gradingScheme = listOf()
            ),
            Lecture(
                name = "Sayısal Analiz",
                code = "BLM1022",
                grade = null,
                credits = 3.0f,
                gradingScheme = listOf()
            ),
        ),
        onLectureClick = { lecture ->
            setSnackBarState(!snackbarVisibleState)
        },
        modifier = Modifier.padding(all = 16.dp),
    )
    if (snackbarVisibleState) {
        Snackbar(
            modifier = Modifier.padding(8.dp),

        ) { Text(text = "This is a snackbar!") }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListScreenPreview() {
    UniGradeTheme {
        ListScreen()
    }
}

@Composable
fun LectureItem(lecture: Lecture, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onBackground.copy(alpha = .12f)
        ),
        onClick = onClick
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
                    style = MaterialTheme.typography.overline.copy(
                        color = MaterialTheme.colors.onBackground.copy(alpha = .6f)
                    ),
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(6.dp))
            Column(
                modifier = Modifier
                    .width(56.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = lecture.grade?.name ?: "—",
                    style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.primary),
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(
                        id = lecture.grade?.localizedTextResId ?: R.string.pending
                    ),
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontSize = 8.sp,
                        color = MaterialTheme.colors.onBackground.copy(alpha = .6f)
                    ),
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
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(id = R.string.credits),
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontSize = 8.sp,
                        color = MaterialTheme.colors.onBackground.copy(alpha = .6f)
                    ),
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
fun LectureList(
    lectures: List<Lecture>,
    onLectureClick: (Lecture) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(items = lectures) { lecture ->
            Row {
                Spacer(modifier = Modifier.width(12.dp))
                val secondaryColor = MaterialTheme.colors.secondary
                Canvas(modifier = Modifier.width(16.dp).height(58.dp)) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height
                    drawCircle(
                        color = secondaryColor,
                        center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                        radius = 8.dp.toPx()
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                LectureItem(
                    lecture = lecture,
                    onClick = { onLectureClick(lecture) }
                )
            }
        }
    }
}

@Preview
@Composable
fun LectureListPreview() {
    UniGradeTheme {
        LectureList(
            lectures = listOf(
                Lecture(
                    name = "Bilgisayar Bilimlerine Giriş",
                    code = "BLM1011",
                    grade = Grade.BB,
                    credits = 4.0f,
                    gradingScheme = listOf()
                ),
                Lecture(
                    name = "Matematik 1",
                    code = "BLM1072",
                    grade = Grade.FF,
                    credits = 4.0f,
                    gradingScheme = listOf()
                ),
                Lecture(
                    name = "Sayısal Analiz",
                    code = "BLM1022",
                    grade = null,
                    credits = 3.0f,
                    gradingScheme = listOf()
                ),
            ),
            onLectureClick = {}
        )
    }
}