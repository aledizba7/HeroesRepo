package com.example.heroesapp.models

data class User(val name:String,val email:String,val password: String){
    val computedName :String get() = "$name, regresaste"
    companion object{
        val users = listOf(
            User("Alejandra","ale@gmail.com","12345"),
            User("Gabriela","gaby@gmail.com","12345"),
            User("Fernanda","fer@gmail.com","12345")
        )
    }
}