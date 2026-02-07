package com.example.viikko1.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val dueDate: String,
    val done: Boolean = false
)


val mockTasks = listOf(
    Task(1, "Kauppa", "Osta huomisen ruuat", "2026-05-15"),
    Task(2, "Koulu", "Tee kotitehtävät", "2026-05-10"),
    Task(3, "Treeni", "Käy salilla", "2026-05-12"),
    Task(4, "Valmennukset", "Suunnitele treenit", "2026-05-11"),
    Task(5, "Siivous", "Imuroi", "2026-05-20")
)

