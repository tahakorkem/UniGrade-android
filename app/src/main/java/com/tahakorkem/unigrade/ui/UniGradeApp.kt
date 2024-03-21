package com.tahakorkem.unigrade.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tahakorkem.unigrade.ui.screen.lecture.addoredit.AddOrEditLectureScreen
import com.tahakorkem.unigrade.ui.screen.lecture.detail.LectureDetailScreen
import com.tahakorkem.unigrade.ui.screen.lecture.list.LectureListScreen
import com.tahakorkem.unigrade.ui.theme.UniGradeTheme
import com.tahakorkem.unigrade.ui.NavDestinations.Lecture as LectureDest

@Composable
fun UniGradeApp() {
    UniGradeTheme {
        val navController = rememberNavController()
        val navigateUp: () -> Unit = { navController.navigateUp() }

        NavHost(
            navController = navController,
            startDestination = LectureDest.List.ROUTE
        ) {
            composable(route = LectureDest.List.ROUTE) {
                LectureListScreen(
                    navigateToDetail = { lecture ->
                        navController.navigate(LectureDest.Detail.routeWithArgs(lecture.code))
                    },
                    navigateToAdd = {
                        navController.navigate(LectureDest.Add.ROUTE)
                    }
                )
            }
            composable(
                route = LectureDest.Detail.ROUTE,
                arguments = LectureDest.Detail.ARGUMENTS
            ) { backStackEntry ->
                val courseCode = backStackEntry.arguments
                    ?.getString(LectureDest.Detail.KEY_CODE)
                    ?: error("No lecture code")
                LectureDetailScreen(
                    courseCode = courseCode,
                    navigateToEdit = { lecture ->
                        navController.navigate(LectureDest.Edit.routeWithArgs(lecture.code))
                    },
                    navigateUp = navigateUp
                )
            }
            composable(
                route = LectureDest.Edit.ROUTE,
                arguments = LectureDest.Edit.ARGUMENTS
            ) { backStackEntry ->
                val courseCode = backStackEntry.arguments
                    ?.getString(LectureDest.Edit.KEY_CODE)
                    ?: error("No lecture code")
                AddOrEditLectureScreen(
                    courseCode = courseCode,
                    navigateUp = navigateUp
                )
            }
            composable(
                route = LectureDest.Add.ROUTE
            ) {
                AddOrEditLectureScreen(
                    navigateUp = navigateUp
                )
            }
        }
    }
}