package com.example.viikko1.domain

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val dueDate: String, // Esim. "2024-05-10"
    val done: Boolean = false
)


val mockTasks = listOf(
    Task(1, "Kauppa", "Osta huomisen ruuat", "2024-05-15"),
    Task(2, "Koulu", "Tee kotitehtävät", "2024-05-10"),
    Task(3, "Treeni", "Käy salilla", "2024-05-12"),
    Task(4, "Valmennukset", "Suunnitele treenit", "2024-05-11"),
    Task(5, "Siivous", "Imuroi", "2024-05-20")
)



fun addTask(list: List<Task>, task:Task): List <Task> = list + task

fun Done(list: List<Task>, id: Int):List<Task> {
    return list.map {
        if (it.id == id) it.copy(done = !it.done) else it
    }
}

fun filterByDone(list: List<Task>, done: Boolean): List<Task> = list.filter {it.done ==done}

fun sortByDueDate(list: List<Task>): List<Task> = list.sortedBy { it.dueDate }