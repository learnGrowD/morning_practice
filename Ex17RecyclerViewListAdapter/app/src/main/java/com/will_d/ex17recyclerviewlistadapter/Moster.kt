package com.will_d.ex17recyclerviewlistadapter

data class Moster(
    val name : String,
    val race : Race,
    val level : Int,
    val stats : List<Int>,
    val encount : Boolean,
)

//이거 뭥미 왜쓰는 거임...
enum class Race {
    Zombie, Human, Goblin, Dragon,
}
