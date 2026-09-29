package com.example.studentmanager.ui.navigation

object Routes {
    const val SPLASH = "splash"
    const val STUDENT_LIST = "student_list"
    const val ADD_STUDENT = "add_student"
    const val EDIT_STUDENT = "edit_student/{studentId}"

    fun editStudent(studentId: String) = "edit_student/$studentId"
}
