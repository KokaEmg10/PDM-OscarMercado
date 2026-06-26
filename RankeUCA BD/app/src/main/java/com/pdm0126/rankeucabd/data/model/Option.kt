package com.pdm0126.rankeucabd.data.model

import com.pdm0126.rankeucabd.data.database.entities.OptionEntity

data class Option(
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val questionId: Int = 0,
    val votes: Int = 0
)

fun Option.toEntity(): OptionEntity {
    return OptionEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        questionId = questionId,
        votes = votes
    )
}
