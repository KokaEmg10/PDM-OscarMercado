package com.pdm0126.rankeucabd.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.pdm0126.rankeucabd.data.model.Question

data class QuestionWithOptions(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val options: List<OptionEntity>
)

fun QuestionWithOptions.toModel(): Question {
    return Question(
        id = question.id,
        title = question.title,
        optionCount = options.size,
        totalVotes = question.totalVotes
    )
}
