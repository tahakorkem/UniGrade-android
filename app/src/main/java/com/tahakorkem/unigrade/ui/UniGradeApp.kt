package com.tahakorkem.unigrade.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tahakorkem.unigrade.ui.screen.lecturedetails.LectureDetailScreen
import com.tahakorkem.unigrade.ui.screen.lecturelist.LectureListScreen
import com.tahakorkem.unigrade.ui.theme.UniGradeTheme

@Composable
fun UniGradeApp() {
    UniGradeTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = NavDestinations.LECTURE_LIST_ROUTE
        ) {
            composable(route = NavDestinations.LECTURE_LIST_ROUTE) {
                LectureListScreen(navController = navController)
            }
            composable(
                route = "${NavDestinations.LECTURE_DETAIL_ROUTE}/{${NavDestinations.LECTURE_DETAIL_CODE_KEY}}",
                arguments = listOf(
                    navArgument(NavDestinations.LECTURE_DETAIL_CODE_KEY) { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val courseCode = backStackEntry.arguments?.getString(NavDestinations.LECTURE_DETAIL_CODE_KEY)
                    ?: error("No lecture code")
                LectureDetailScreen(
                    courseCode = courseCode,
                )
            }
        }
    }
}