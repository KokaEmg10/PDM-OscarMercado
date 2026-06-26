package com.pdm0126.rankeucabd.data.repository

import com.pdm0126.rankeucabd.data.model.Option
import kotlinx.coroutines.flow.Flow

interface OptionRepository {
    fun getOptions(): Flow<List<Option>>
    fun getOptions(questionId: Int): Flow<List<Option>>
    suspend fun addOption(option: Option)
    suspend fun addOption(name: String, imageUrl: String, questionId: Int)
    suspend fun deleteOption(option: Option)
    suspend fun voteOption(option: Option)
}
