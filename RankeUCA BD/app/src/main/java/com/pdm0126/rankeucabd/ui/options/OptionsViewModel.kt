package com.pdm0126.rankeucabd.ui.options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm0126.rankeucabd.RankeUCAApplication
import com.pdm0126.rankeucabd.data.model.Option
import com.pdm0126.rankeucabd.data.repository.OptionRepository
import com.pdm0126.rankeucabd.data.repository.QuestionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OptionsViewModel(
    private val optionRepository: OptionRepository,
    private val questionRepository: QuestionRepository,
    private val questionId: Int
) : ViewModel() {

    val options: StateFlow<List<Option>> = optionRepository.getOptions(questionId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun addOption(name: String, imageUrl: String) {
        viewModelScope.launch {
            optionRepository.addOption(name, imageUrl, questionId)
        }
    }

    fun deleteOption(option: Option) {
        viewModelScope.launch {
            optionRepository.deleteOption(option)
        }
    }

    fun voteOption(option: Option) {
        viewModelScope.launch {
            optionRepository.voteOption(option)
            questionRepository.incrementTotalVotes(questionId)
        }
    }

    companion object {
        fun provideFactory(questionId: Int) = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RankeUCAApplication
                OptionsViewModel(
                    app.appProvider.provideOptionRepository(),
                    app.appProvider.provideQuestionRepository(),
                    questionId
                )
            }
        }
    }
}
