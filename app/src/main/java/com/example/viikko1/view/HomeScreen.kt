package com.example.viikko1.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.model.Task
import com.example.viikko1.viewmodel.TaskViewModel
import androidx.compose.runtime.collectAsState


@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel = viewModel()
) {
    val tasks by taskViewModel.tasks.collectAsState()
    var newTaskTitle by remember { mutableStateOf("") }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Text("Tehtävälista", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                modifier = Modifier.weight(1f),
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Uusi tehtävä") }
            )

            Button(onClick = {
                if (newTaskTitle.isNotBlank()) {
                    val newId =
                        if (tasks.isEmpty()) 1 else tasks.maxBy { it.id }.id + 1

                    taskViewModel.addTask(
                        Task(
                            id = newId,
                            title = newTaskTitle,
                            description = "Lisätty käyttäjältä",
                            dueDate = "2024-05-30"
                        )
                    )
                    newTaskTitle = ""
                }
            }) {
                Text("Lisää")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            items(tasks) { task ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = task.done,
                                onCheckedChange = { taskViewModel.toggleDone(task.id) }
                            )
                            Text(
                                text = task.title,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                ))
                        }

                        Button(onClick = { selectedTask = task }) {
                            Text("Avaa")
                        }
                    }


                    if (task.description.isNotBlank()) {
                        Text(
                            text = task.description,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize * 1.1f
                            ),
                            modifier = Modifier.padding(start = 15.dp, top = 2.dp)


                        )
                    }
                }
            }
        }

        selectedTask?.let { task ->
            DetailScreen(
                task = task,
                onDismiss = { selectedTask = null },
                onSave = { taskViewModel.updateTask(it) },
                onDelete = {
                    taskViewModel.removeTask(task.id)
                    selectedTask = null
                }
            )
        }
    }
}
