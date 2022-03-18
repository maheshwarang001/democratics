package com.example.democratics.MainStreamChat

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DaoStream {
    private val databaseReference: DatabaseReference
    fun add(modelInputStream: ModelInputStream?): Task<Void> {
        return databaseReference.push().setValue(modelInputStream)
    }

    init {
        val db = FirebaseDatabase.getInstance("https://democratics-default-rtdb.asia-southeast1.firebasedatabase.app")
        databaseReference = db.getReference(ModelInputStream::class.java.simpleName)
    }
}