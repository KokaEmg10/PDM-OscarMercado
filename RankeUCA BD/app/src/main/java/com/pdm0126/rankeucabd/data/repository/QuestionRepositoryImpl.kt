package com.pdm0126.rankeucabd.data.repository

import com.pdm0126.rankeucabd.data.database.dao.QuestionDao
import com.pdm0126.rankeucabd.data.database.entities.QuestionEntity
import com.pdm0126.rankeucabd.data.database.entities.toModel
import com.pdm0126.rankeucabd.data.model.Question
import com.pdm0126.rankeucabd.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestionRepositoryImpl(
    private val questionDao: QuestionDao
) : QuestionRepository {

    override fun getQuestions(): Flow<List<Question>> {
        return questionDao.getQuestionsWithOptions().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun addQuestion(title: String) {
        questionDao.insertQuestion(QuestionEntity(title = title))
    }

    override suspend fun deleteQuestion(question: Question) {
        questionDao.deleteQuestion(question.toEntity())
    }

    override suspend fun incrementTotalVotes(questionId: Int) {
        questionDao.getQuestionById(questionId)?.let { questionEntity ->
            val updatedQuestion = questionEntity.copy(totalVotes = questionEntity.totalVotes + 1)
            questionDao.updateQuestion(updatedQuestion)
        }
    }
}
