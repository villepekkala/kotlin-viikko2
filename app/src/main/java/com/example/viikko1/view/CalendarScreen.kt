package com.example.viikko1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.viikko1.model.Task
import com.example.viikko1.viewmodel.TaskViewModel
import androidx.compose.runtime.collectAsState


fun formatToFinnishDate(isoDate: String): String {
    if (isoDate == "Ei päivämäärää") return isoDate


    val parts = isoDate.split("-")
    if (parts.size == 3) {
        val year = parts[0]
        val month = parts[1]
        val day = parts[2]
        return "$day.$month.$year"
    }
    return isoDate
}

@Composable
fun CalendarScreen(taskViewModel: TaskViewModel) {

    val tasks by taskViewModel.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }


    val groupedTasks = remember(tasks) {
        tasks.groupBy { if (it.dueDate.isBlank()) "Ei päivämäärää" else it.dueDate }
            .toSortedMap()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Kalenteri",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Tehtäviä yhteensä: ${tasks.size}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (tasks.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Ei tehtäviä. Lisää tehtävä 'Lista'-näkymässä.")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                groupedTasks.forEach { (rawDate, tasksForDate) ->

                    item {

                        val displayDate = formatToFinnishDate(rawDate)

                        Text(
                            text = displayDate,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(top = 16.dp, bottom = 4.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                                .fillMaxWidth()
                                .padding(4.dp)
                        )
                    }

                    items(tasksForDate) { task ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedTask = task },
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = task.done,
                                    onCheckedChange = { taskViewModel.toggleDone(task.id) }
                                )
                                Column {
                                    Text(
                                        text = task.title,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    if (task.description.isNotBlank()) {
                                        Text(
                                            text = task.description,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
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