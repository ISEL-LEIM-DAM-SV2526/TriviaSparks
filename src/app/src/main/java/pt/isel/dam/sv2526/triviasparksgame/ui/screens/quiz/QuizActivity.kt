package pt.isel.dam.sv2526.triviasparksgame.ui.screens.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.TriviaSparksTheme

class QuizActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TriviaSparksTheme {
                QuizActivity()
            }
        }
    }
}