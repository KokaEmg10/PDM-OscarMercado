package com.pdm0126.rankeucabd.data.repository

import com.pdm0126.rankeucabd.data.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getQuestions(): Flow<List<Question>>
    suspend fun addQuestion(title: String)
    suspend fun deleteQuestion(question: Question)
    suspend fun incrementTotalVotes(questionId: Int)
}
