package com.example.learningcompose.data.api.model

import com.example.learningcompose.fragment.Episode

data class Episode(
    val id: String,
    val name: String,
    val episode: String,
    val air_date: String,
    val characters: List<Character>
) {
    constructor(data: Episode) : this(
        data.id ?: "",
        data.name ?: "",
        data.episode ?: "",
        data.air_date ?: "",
        data.characters.map {
            Character(it!!.characterType)
        }
    )
}

fun sampleEpisode() = Episode(
    id = "1",
    name = "Episode 1",
    episode = "s1e1",
    air_date = "2020-01-01",
    characters = listOf(
        sampleCharacter(),
        sampleCharacter(),
        sampleCharacter()
    )
)