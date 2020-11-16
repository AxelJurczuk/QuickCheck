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
    fun loadAbsentStudents(date:String,successListener: DataFetched) {
        val studentsList = mutableListOf<Student>()

        db.collection("ausentes")
            .document(date)
            .collection("absent students")
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

    fun loadSecondStudents(): MutableList<Student> {

        return mutableListOf(
            Student("Gonzalez", "Mauricio", false),
            Student("Martinez", "Mauri", false),
            Student("Perez", "Marco", false),
            Student("Juarez", "Fernando", false),
            Student("Fabbiani", "Marta", false),
            Student("Ulises", "Maria", false),
            Student("Cope", "Florencia", false),
            Student("Colon", "Emanuel", false),
            Student("Barbeito", "Carlitos", false),
            Student("Gonzales", "Mauricio", false),
            Student("Marcunez", "Mauri", false),
            Student("Pirz", "Marco", false),
            Student("Juacoz", "Fernando", false),
            Student("Fatai", "Marta", false),
            Student("Ulimus", "Maria", false),
            Student("Copato", "Florencia", false),
            Student("Casas", "Emanuel", false),
            Student("Barbosa", "Carlitos", false),
        )

    }

    fun loadThirdStudents(successListener: DataFetched) {
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
    }

    fun loadFourthStudents(): MutableList<Student> {
        val studentsList = mutableListOf<Student>()

        db.collection("groups").document("group1").collection("students")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.v("status data students", "${document.id} => ${document.data}")

                    studentsList.add(document.toObject(Student::class.java))
                }
                Log.v("lista", "$studentsList")
            }

        return studentsList

    }

    fun loadFifthStudents(): MutableList<Student> {
        val studentsList = mutableListOf<Student>()

        db.collection("groups").document("group1").collection("students")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.v("status data students", "${document.id} => ${document.data}")

                    studentsList.add(document.toObject(Student::class.java))
                }
                Log.v("lista", "$studentsList")
            }

        return studentsList
    }

    interface DataFetched {
        fun onFetched(studentList: List<Student>)
    }

}