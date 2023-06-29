package com.aplicativos.listacompose.datasource

import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by João Bosco on 29/06/2023.
 */
class DataSource {

    private val db = FirebaseFirestore.getInstance()

    fun saveTask(task: String, description: String, priority: Int) {
        val taskMap = hashMapOf(
            "tarefa" to task,
            "descricao" to description,
            "prioridade" to priority
        )
        db.collection("tarefas").document(task).set(taskMap)
            .addOnCompleteListener {  }
            .addOnFailureListener {  }
    }
}