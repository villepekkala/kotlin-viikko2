package com.example.viikko1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko1.domain.*

@Composable
fun HomeScreen() {

    var tasks by remember { mutableStateOf(mockTasks) }

    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "TEHTÄVÄLISTA")


        Row {
            Button(onClick = {
                val uusiId = tasks.size + 1
                tasks = addTask(tasks, Task(uusiId, "Uusi $uusiId", "Kuvaus", "2024-12-01", false))
            }) {
                Text("Lisää")
            }

            Button(onClick = { tasks = sortByDueDate(tasks) }) {
                Text("Sorteeraa")
            }

            Button(onClick = { tasks = filterByDone(tasks, true) }) {
                Text("Vain valmiit")
            }
        }


        LazyColumn {
            items(tasks) { task ->
                Row(modifier = Modifier.padding(vertical = 5.dp)) {

                    Text(text = "${task.title} - ${task.dueDate} - Done: ${task.done}")


                    Button(onClick = { tasks = Done(tasks, task.id) }) {
                        Text("Valmis")
                    }
                }
            }
        }
    }
}