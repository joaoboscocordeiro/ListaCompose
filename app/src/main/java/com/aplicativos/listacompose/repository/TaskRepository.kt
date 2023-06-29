package com.aplicativos.listacompose.repository

import com.aplicativos.listacompose.datasource.DataSource
import com.aplicativos.listacompose.model.Tarefa
import kotlinx.coroutines.flow.Flow

/**
 * Created by João Bosco on 29/06/2023.
 */
class TasksRepository {

    private val dataSource = DataSource()

    fun saveTask(task: String, description: String, priority: Int) {
        dataSource.saveTask(task, description, priority)
    }

    fun getAllTasks(): Flow<MutableList<Tarefa>> {
        return dataSource.getAllTasks()
    }
}