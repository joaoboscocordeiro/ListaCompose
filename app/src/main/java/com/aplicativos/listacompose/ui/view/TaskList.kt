package com.aplicativos.listacompose.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aplicativos.listacompose.R
import com.aplicativos.listacompose.itemlist.ItemTask
import com.aplicativos.listacompose.model.Tarefa
import com.aplicativos.listacompose.ui.theme.Black
import com.aplicativos.listacompose.ui.theme.White

/**
 * Created by João Bosco on 26/06/2023.
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskList(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Gray,
                title = {
                    Text(
                        text = "Lista de Tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
            )
        },
        backgroundColor = Black,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("TaskSave")
                },
                backgroundColor = Color.Gray
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.ic_add
                    ),
                    contentDescription = "Ícone de Adicionar Tarefas."
                )
            }
        }
    ) {
        val listaTarefas: MutableList<Tarefa> = mutableListOf(
            Tarefa(
                tarefa = "Jogar Futebol",
                descricao = "Quadrão do Corinthians",
                prioridade = 0
            ),
            Tarefa(
                tarefa = "Ir ao Mercado",
                descricao = "Assai",
                prioridade = 1
            ),
            Tarefa(
                tarefa = "Ir a Igreja",
                descricao = "Tocar com o pessoal",
                prioridade = 2
            ),
            Tarefa(
                tarefa = "Sair com a Esposa",
                descricao = "Jantar Juntos.",
                prioridade = 3
            )
        )

        LazyColumn {
            itemsIndexed(listaTarefas) { position, _ ->
                ItemTask(position, listaTarefas)
            }
        }
    }
}