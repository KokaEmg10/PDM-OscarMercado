package com.pdm0126.rankeucabd.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdm0126.rankeucabd.data.model.Question

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val totalVotes: Int = 0
)

fun QuestionEntity.toModel(optionCount: Int = 0): Question {
    return Question(
        id = id,
        title = title,
        optionCount = optionCount,
        totalVotes = totalVotes
    )
}
