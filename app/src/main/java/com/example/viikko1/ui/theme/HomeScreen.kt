package com.example.viikko1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.viikko1.domain.*

@Composable
fun HomeScreen() {

    var originalTasks by remember { mutableStateOf(mockTasks) } // var, jotta voidaan päivittää
    var tasks by remember { mutableStateOf(originalTasks) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Tehtävälista",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // Suodattaa vain valmiit tehtävät
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    tasks = tasks.filter { it.done }
                }
            ) {
                Text("Vain valmiit")
            }


            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    tasks = sortByDueDate(tasks)
                }
            ) {
                Text("Järjestä")
            }


            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    tasks = originalTasks
                }
            ) {
                Text("Palauta")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val newTask = Task(
                    id = tasks.size + 1,
                    title = "Uusi tehtävä",
                    description = "Lisätty napista",
                    dueDate = "2024-05-30"
                )
                tasks = addTask(tasks, newTask)
                originalTasks = addTask(originalTasks, newTask) // päivitetään myös alkuperäinen lista
            }
        ) {
            Text("Lisää tehtävä")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks) { task ->

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(task.title, fontWeight = FontWeight.Medium)
                    Text(task.description)
                    Text("Määräpäivä: ${task.dueDate}")

                    Text(
                        text = if (task.done) "Valmis" else "Kesken",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))


                    Button(onClick = {
                        val updatedTasks = Done(tasks, task.id)
                        tasks = updatedTasks
                        originalTasks = Done(originalTasks, task.id) // päivitetään myös alkuperäinen lista
                    }) {
                        Text(if (task.done) "Peru" else "Valmis")
                    }

                    Divider(modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}
