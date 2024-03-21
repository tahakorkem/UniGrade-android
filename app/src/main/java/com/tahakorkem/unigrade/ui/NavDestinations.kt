package com.tahakorkem.unigrade.ui

import androidx.navigation.NavType
import androidx.navigation.navArgument

object NavDestinations {
    object Lecture {
        object List {
            const val ROUTE = "lectures"
        }

        object Detail {
            private const val ROUTE_PREFIX = "lecture"
            const val KEY_CODE = "lectureCode"
            const val ROUTE = "$ROUTE_PREFIX/{$KEY_CODE}"
            val ARGUMENTS = listOf(navArgument(KEY_CODE) { type = NavType.StringType })
            fun routeWithArgs(code: String) = "$ROUTE_PREFIX/$code"
        }

        object Add {
            const val ROUTE = "lecture/add"
        }

        object Edit {
            private const val ROUTE_PREFIX = "lecture/edit"
            const val KEY_CODE = "lectureCode"
            const val ROUTE = "$ROUTE_PREFIX/{$KEY_CODE}"
            val ARGUMENTS = listOf(navArgument(KEY_CODE) { type = NavType.StringType })
            fun routeWithArgs(code: String) = "$ROUTE_PREFIX/$code"
        }
    }
}