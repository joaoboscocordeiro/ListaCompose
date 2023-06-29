package com.aplicativos.listacompose.ui.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aplicativos.listacompose.R
import com.aplicativos.listacompose.constatnts.Constants
import com.aplicativos.listacompose.repository.TasksRepository
import com.aplicativos.listacompose.ui.theme.RadioButtonGreenDisabled
import com.aplicativos.listacompose.ui.theme.RadioButtonGreenSelected
import com.aplicativos.listacompose.ui.theme.RadioButtonRedDisabled
import com.aplicativos.listacompose.ui.theme.RadioButtonRedSelected
import com.aplicativos.listacompose.ui.theme.RadioButtonYellowDisabled
import com.aplicativos.listacompose.ui.theme.RadioButtonYellowSelected
import com.aplicativos.listacompose.ui.theme.White
import com.aplicativos.listacompose.util.BackgroundText
import com.aplicativos.listacompose.util.EdgeButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by João Bosco on 26/06/2023.
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskSave(
    navController: NavController
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tasksRepository = TasksRepository()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Gray,
                title = {
                    Text(
                        text = stringResource(R.string.txt_title_toolbar),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
            )
        }
    ) {

        var title by remember {
            mutableStateOf("")
        }

        var description by remember {
            mutableStateOf("")
        }

        val emptyPriority by remember {
            mutableStateOf(false)
        }

        var lowPriority by remember {
            mutableStateOf(false)
        }

        var mediumPriority by remember {
            mutableStateOf(false)
        }

        var highPriority by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            BackgroundText(
                value = title,
                onValueChange = {
                    title = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = stringResource(R.string.hint_title),
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )

            BackgroundText(
                value = description,
                onValueChange = {
                    description = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                label = stringResource(R.string.hint_description),
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(text = stringResource(R.string.txt_priority))

                RadioButton(
                    selected = lowPriority,
                    onClick = {
                        lowPriority = !lowPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonGreenDisabled,
                        selectedColor = RadioButtonGreenSelected
                    )
                )

                RadioButton(
                    selected = mediumPriority,
                    onClick = {
                        mediumPriority = !mediumPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonYellowDisabled,
                        selectedColor = RadioButtonYellowSelected
                    )
                )

                RadioButton(
                    selected = highPriority,
                    onClick = {
                        highPriority = !highPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonRedDisabled,
                        selectedColor = RadioButtonRedSelected
                    )
                )
            }

            EdgeButton(
                onClick = {

                    var message = true

                    scope.launch(Dispatchers.IO) {
                        if (title.isEmpty()) {
                            message = false
                        } else if (description.isEmpty()) {
                            message = false
                        }
                        else if (title.isNotEmpty() && description.isNotEmpty() && lowPriority) {
                            tasksRepository.saveTask(title, description, Constants.LOW_PRIORITY)
                            message = true
                        } else if (title.isNotEmpty() && description.isNotEmpty() && mediumPriority) {
                            tasksRepository.saveTask(title, description, Constants.MEDIUM_PRIORITY)
                            message = true
                        } else if (title.isNotEmpty() && description.isNotEmpty() && highPriority) {
                            tasksRepository.saveTask(title, description, Constants.HIGH_PRIORITY)
                            message = true
                        } else if (title.isNotEmpty() && description.isNotEmpty() && emptyPriority) {
                            tasksRepository.saveTask(title, description, Constants.NO_PRIORITY)
                            message = true
                        } else {
                            tasksRepository.saveTask(title, description, Constants.NO_PRIORITY)
                            message = true
                        }
                    }

                    scope.launch(Dispatchers.Main) {
                        if (message) {
                            Toast.makeText(context, "Sucesso ao salvar a tarefa", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Título e Descrição da tarefa é obrigatório.", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(20.dp),
                text = stringResource(R.string.btn_save)
            )
        }
    }
}