package com.example.r504tl1973004_jsontypicodeusers.presentation


// UsersState.kt



// User data class sisältää sen tiedon, mitä
// käyttöliittymässä näytetään jokaisesta käyttäjästä
data class User(val id: Int, val email: String)

// UsersState on luokka, jonka datan mukaan käyttöliittymä päivitetään
data class UsersState(
    // loading: tämän mukaan näytetään latausikoni
    // jos tietojen haku kestää pitkään
    val loading: Boolean = false,
    // tämä on lista käyttäjiä (User data class)
    val items: List<User> = emptyList(),
    // jos haku ei onnistu, näytetään käyttäjälle virhe
    val error: String? = null
)
