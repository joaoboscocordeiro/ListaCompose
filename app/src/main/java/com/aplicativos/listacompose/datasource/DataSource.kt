package com.aplicativos.listacompose.datasource

import com.aplicativos.listacompose.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by João Bosco on 29/06/2023.
 */
class DataSource {

    private val db = FirebaseFirestore.getInstance()

    private val _allTask = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private val allTask: StateFlow<MutableList<Tarefa>> = _allTask

    fun saveTask(task: String, description: String, priority: Int) {
        val taskMap = hashMapOf(
            "tarefa" to task,
            "descricao" to description,
            "prioridade" to priority
        )
        db.collection("tarefas").document(task).set(taskMap)
            .addOnCompleteListener { }
            .addOnFailureListener { }
    }

    fun getAllTasks(): Flow<MutableList<Tarefa>> {

        val listTask: MutableList<Tarefa> = mutableListOf()

        db.collection("tarefas").get()
            .addOnCompleteListener { querySnapshot ->
                if (querySnapshot.isSuccessful) {
                    for (document in querySnapshot.result) {
                        val task = document.toObject(Tarefa::class.java)
                        listTask.add(task)
                        _allTask.value = listTask
                    }
                }
            }
        return allTask
    }

    fun deleteTask(task: String) {
        db.collection("tarefas").document(task).delete()
            .addOnCompleteListener {  }
            .addOnFailureListener {  }
    }
}