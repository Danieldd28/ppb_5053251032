package com.example.studentmanager.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class StudentViewModel : ViewModel() {

    private val dummyStudents = listOf(
        Student(nim = "2021001", name = "Budi Santoso", major = "Informatika"),
        Student(nim = "2021002", name = "Siti Aminah", major = "Sistem Informasi"),
        Student(nim = "2021003", name = "Andi Wijaya", major = "Desain Komunikasi Visual")
    )

    private val _students = MutableStateFlow(dummyStudents)
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val filteredStudents: StateFlow<List<Student>> = combine(_students, _searchQuery) { list, query ->
        if (query.isBlank()) {
            list
        } else {
            list.filter {
                it.name.contains(query, ignoreCase = true) ||
                    it.nim.contains(query, ignoreCase = true) ||
                    it.major.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(scope, kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000), dummyStudents)

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun getStudentById(id: String): Student? = _students.value.find { it.id == id }

    fun addStudent(nim: String, name: String, major: String) {
        _students.value = _students.value + Student(nim = nim, name = name, major = major)
    }

    fun updateStudent(id: String, nim: String, name: String, major: String) {
        _students.value = _students.value.map {
            if (it.id == id) it.copy(nim = nim, name = name, major = major) else it
        }
    }

    fun deleteStudent(id: String) {
        _students.value = _students.value.filterNot { it.id == id }
    }

    fun refresh() {
        _searchQuery.value = ""
    }
}
