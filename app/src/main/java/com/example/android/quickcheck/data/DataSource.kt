package com.example.android.quickcheck.data

import android.util.Log
import com.example.android.quickcheck.model.Student
import com.google.firebase.firestore.FirebaseFirestore

class DataSource {
    // Access a Cloud Firestore instance from your Activity
    private val db = FirebaseFirestore.getInstance()

    fun loadFirstStudents(successListener: DataFetched) {
        val studentsList = mutableListOf<Student>()

        db.collection("groups")
            .document("group1")
            .collection("students")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    studentsList.add(document.toObject(Student::class.java))
                }
                successListener.onFetched(studentsList)
            }
            .addOnFailureListener { exception ->
                Log.d("download failed", "downloading failed", exception)
            }
    }

    fun loadSecondStudents(successListener: DataFetched) {
        val studentsList = mutableListOf<Student>()

        db.collection("groups")
            .document("group2")
            .collection("students")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    studentsList.add(document.toObject(Student::class.java))
                }
                successListener.onFetched(studentsList)
            }
            .addOnFailureListener { exception ->
                Log.d("download failed", "downloading failed", exception)
            }
    }


    fun loadThirdStudents(successListener: DataFetched) {
        val studentsList = mutableListOf<Student>()

        db.collection("groups")
            .document("group3")
            .collection("students")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    studentsList.add(document.toObject(Student::class.java))

                }
                successListener.onFetched(studentsList)
            }
    }

    fun loadFourthStudents(successListener: DataFetched) {
        val studentsList = mutableListOf<Student>()

        db.collection("groups")
            .document("group4")
            .collection("students")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    studentsList.add(document.toObject(Student::class.java))

                }
                successListener.onFetched(studentsList)
            }
    }

    fun loadFifthStudents(successListener: DataFetched) {
        val studentsList = mutableListOf<Student>()

        db.collection("groups")
            .document("group5")
            .collection("students")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    studentsList.add(document.toObject(Student::class.java))

                }
                successListener.onFetched(studentsList)
            }
    }

    fun loadAbsentStudents(date:String,group:String,successListener: DataFetched) {
        val studentsList = mutableListOf<Student>()

        db.collection("ausentes")
            .document(date)
            .collection(group)
            .get()
            .addOnSuccessListener {documents ->
                for (document in documents) {
                    studentsList.add(document.toObject(Student::class.java))
                }
                successListener.onFetched(studentsList)
            }
            .addOnFailureListener { exception ->
                Log.d("download failed", "download failed", exception)
            }
    }

    interface DataFetched {
        fun onFetched(studentList: List<Student>)
    }

}