package com.pdm0126.rankeucabd.data.repository

import com.pdm0126.rankeucabd.data.model.Option
import kotlinx.coroutines.flow.Flow

interface OptionRepository {
    fun getOptions(): Flow<List<Option>>
    suspend fun addOption(option: Option)
    suspend fun deleteOption(option: Option)
}