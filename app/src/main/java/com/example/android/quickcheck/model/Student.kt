package com.example.android.quickcheck.model

//Firebase: Cada clase personalizada debe tener un constructor público que no acepte argumentos.
//Además, la clase debe incluir un captador público para cada propiedad.

data class Student (val lastName: String="", val name:String="", var status:Boolean=false)