package com.example.viikko1.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.viikko1.model.Task
import com.example.viikko1.viewmodel.TaskViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun HomeScreen(taskViewModel: TaskViewModel) {
    val tasks by taskViewModel.tasks.collectAsState()


    var selectedTask by remember { mutableStateOf<Task?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column {
            Text("Tehtävälista", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(tasks) { task ->
                    TaskItemCard(
                        task = task,
                        onToggle = { taskViewModel.toggleDone(task.id) },
                        onClick = { selectedTask = task }
                    )
                }
            }
        }


        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Lisää tehtävä")
        }
    }


    if (showAddDialog) {
        DetailScreen(
            task = null,
            onDismiss = { showAddDialog = false },
            onSave = { newTask ->

                val newId = if (tasks.isEmpty()) 1 else tasks.maxBy { it.id }.id + 1
                taskViewModel.addTask(newTask.copy(id = newId))
            },
            onDelete = {}
        )
    }


    selectedTask?.let { task ->
        DetailScreen(
            task = task,
            onDismiss = { selectedTask = null },
            onSave = { updatedTask -> taskViewModel.updateTask(updatedTask) },
            onDelete = {
                taskViewModel.removeTask(task.id)
                selectedTask = null
            }
        )
    }
}


@Composable
fun TaskItemCard(task: Task, onToggle: () -> Unit, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = task.done, onCheckedChange = { onToggle() })
            Text(
                text = task.title,
                style = if (task.done) LocalTextStyle.current.copy(textDecoration = TextDecoration.LineThrough) else LocalTextStyle.current,
                modifier = Modifier.weight(1f)
            )
        }
    }
}