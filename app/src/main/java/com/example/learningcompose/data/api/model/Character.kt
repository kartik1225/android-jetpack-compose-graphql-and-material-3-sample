package com.example.learningcompose.data.api.model

import com.example.learningcompose.fragment.CharacterType


data class Character(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val location: Location
) {
    constructor(data: CharacterType) : this(
        id = data.id ?: "",
        name = data.name ?: "",
        status = data.status ?: "",
        species = data.species ?: "",
        type = data.type ?: "",
        gender = data.gender ?: "",
        image = data.image ?: "",
        location = Location(data.location!!.locationType)
    )
}

fun sampleCharacter() = Character(
    "1",
    "Rick Sanchez",
    "Alive",
    "Human",
    "",
    "Male",
    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    sampleLocation()
)