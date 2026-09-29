package com.example.studentmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.studentmanager.data.StudentViewModel
import com.example.studentmanager.ui.navigation.Routes
import com.example.studentmanager.ui.screens.splash.SplashScreen
import com.example.studentmanager.ui.screens.studentform.StudentFormScreen
import com.example.studentmanager.ui.screens.studentlist.StudentListScreen
import com.example.studentmanager.ui.theme.StudentManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentManagerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    StudentManagerApp()
                }
            }
        }
    }
}

@Composable
fun StudentManagerApp() {
    val navController = rememberNavController()
    val studentViewModel: StudentViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen(onTimeout = {
                navController.navigate(Routes.STUDENT_LIST) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            })
        }
        composable(Routes.STUDENT_LIST) {
            StudentListScreen(
                viewModel = studentViewModel,
                onAddStudent = { navController.navigate(Routes.ADD_STUDENT) },
                onEditStudent = { studentId -> navController.navigate(Routes.editStudent(studentId)) }
            )
        }
        composable(Routes.ADD_STUDENT) {
            StudentFormScreen(
                viewModel = studentViewModel,
                studentId = null,
                onDone = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.EDIT_STUDENT,
            arguments = listOf(navArgument("studentId") { type = NavType.StringType })
        ) { backStackEntry ->
            StudentFormScreen(
                viewModel = studentViewModel,
                studentId = backStackEntry.arguments?.getString("studentId"),
                onDone = { navController.popBackStack() }
            )
        }
    }
}
