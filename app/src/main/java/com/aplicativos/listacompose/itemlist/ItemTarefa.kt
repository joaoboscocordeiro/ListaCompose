package com.aplicativos.listacompose.itemlist

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.aplicativos.listacompose.R
import com.aplicativos.listacompose.model.Tarefa
import com.aplicativos.listacompose.repository.TasksRepository
import com.aplicativos.listacompose.ui.theme.RadioButtonGreenSelected
import com.aplicativos.listacompose.ui.theme.RadioButtonRedSelected
import com.aplicativos.listacompose.ui.theme.RadioButtonYellowSelected
import com.aplicativos.listacompose.ui.theme.ShapeCardPriority
import com.aplicativos.listacompose.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by João Bosco on 26/06/2023.
 */

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ItemTask(
    position: Int,
    listTasks: MutableList<Tarefa>,
    context: Context,
    navController: NavController
) {

    val titleTask = listTasks[position].tarefa
    val descTask = listTasks[position].descricao
    val priority = listTasks[position].prioridade

    val scope = rememberCoroutineScope()
    val repository = TasksRepository()

    fun dialogDelete() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar Tarefa")
            .setMessage("Deseja deletar tarefa ?")
            .setPositiveButton("Sim") { _, _ ->
                repository.deleteTask(titleTask.toString())
                scope.launch(Dispatchers.Main) {
                    listTasks.removeAt(position)
                    navController.navigate("TaskList")
                    Toast.makeText(context, "Sucesso ao deletar tarefa.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Não") { _, _ -> }
            .show()
    }

    val priorityLevel: String = when (priority) {
        0 -> "Sem Prioridade"
        1 -> "Prioridade Baixa"
        2 -> "Prioridade Média"
        else -> "Prioridade Alta"
    }

    val color = when (priority) {
        0 -> Color.Black
        1 -> RadioButtonGreenSelected
        2 -> RadioButtonYellowSelected
        else -> RadioButtonRedSelected
    }

    Card(
        backgroundColor = White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            val (
                txtTitle,
                txtDescription,
                cardPriority,
                txtPriority,
                btnDelete
            ) = createRefs()

            Text(
                text = titleTask.toString(),
                modifier = Modifier.constrainAs(txtTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = descTask.toString(),
                modifier = Modifier.constrainAs(txtDescription) {
                    top.linkTo(txtTitle.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = priorityLevel,
                modifier = Modifier.constrainAs(txtPriority) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )

            Card(
                backgroundColor = color,
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardPriority) {
                        top.linkTo(txtDescription.bottom, margin = 10.dp)
                        start.linkTo(txtPriority.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                shape = ShapeCardPriority.large
            ) {

            }

            IconButton(
                onClick = {
                    dialogDelete()
                },
                modifier = Modifier.constrainAs(btnDelete) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(cardPriority.end, margin = 30.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview
fun ItemTaskPreview() {
}