package com.example.learningcompose.data.api.model

import com.example.learningcompose.fragment.LocationType


data class Location(val id: String, val dimension: String, val name: String) {
    constructor(data: LocationType): this(
        data.id ?: "",
        data.dimension ?: "",
        data.name ?: ""
    )
}

fun sampleLocation() = Location(
    id = "3",
    dimension = "unknown",
    name = "Citadel of Ricks"
)