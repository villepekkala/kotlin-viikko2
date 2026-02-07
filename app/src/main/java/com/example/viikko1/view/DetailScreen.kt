package com.example.viikko1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko1.model.Task

@Composable
fun DetailScreen(
    task: Task?,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: () -> Unit
) {

    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }
    var dueDate by remember { mutableStateOf(task?.dueDate ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {

            Text(if (task == null) "Lisää uusi tehtävä" else "Muokkaa tehtävää")
        },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Otsikko") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Kuvaus") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Pvm (esim. 2026-05-30)") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {

                val taskToSave = task?.copy(
                    title = title,
                    description = description,
                    dueDate = dueDate
                ) ?: Task(
                    id = 0,
                    title = title,
                    description = description,
                    dueDate = dueDate,
                    done = false
                )

                onSave(taskToSave)
                onDismiss()
            }) {
                Text("Tallenna")
            }
        },
        dismissButton = {
            Row {

                if (task != null) {
                    TextButton(onClick = {
                        onDelete()
                        onDismiss()
                    }) {
                        Text("Poista", color = MaterialTheme.colorScheme.error)
                    }
                }
                TextButton(onClick = onDismiss) {
                    Text("Peruuta")
                }
            }
        }
    )
}