package com.example.studentmanager.data

import java.util.UUID

data class Student(
    val id: String = UUID.randomUUID().toString(),
    val nim: String,
    val name: String,
    val major: String
)

val MAJORS = listOf(
    "Informatika",
    "Sistem Informasi",
    "Teknik Komputer",
    "Desain Komunikasi Visual",
    "Manajemen",
    "Akuntansi"
)
