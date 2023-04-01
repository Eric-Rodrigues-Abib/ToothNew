package com.toothnow.toothapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.toothnow.toothapp.ui.theme.ToothNowTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToothNowTheme {
                registerForm()
            }
        }
    }
}