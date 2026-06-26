package com.pdm0126.rankeucabd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.pdm0126.rankeucabd.ui.options.OptionsScreen
import com.pdm0126.rankeucabd.ui.questions.QuestionsScreen
import com.pdm0126.rankeucabd.ui.theme.RankeUCABDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RankeUCABDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentQuestionId by remember { mutableStateOf<Int?>(null) }

                    if (currentQuestionId == null) {
                        QuestionsScreen(
                            onQuestionClick = { id ->
                                currentQuestionId = id
                            }
                        )
                    } else {
                        OptionsScreen(
                            questionId = currentQuestionId!!,
                            onBack = {
                                currentQuestionId = null
                            }
                        )
                    }
                }
            }
        }
    }
}
