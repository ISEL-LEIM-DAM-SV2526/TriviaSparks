package pt.isel.dam.sv2526.triviasparksgame.ui.screens.category

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.TriviaSparksTheme

class CategoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TriviaSparksTheme {
                CategoryScreen()
            }
        }
    }
}