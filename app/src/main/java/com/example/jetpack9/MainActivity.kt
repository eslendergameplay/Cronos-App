package com.example.jetpack9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack9.navigation.NavManager
import com.example.jetpack9.ui.theme.JetPack9Theme
import com.example.jetpack9.viewModels.CronometroViewModel
import com.example.jetpack9.viewModels.CronosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cronometroVM:CronometroViewModel by viewModels()
        val cronosVM:CronosViewModel by viewModels()
        setContent {
            JetPack9Theme {
                NavManager(cronometroVM = cronometroVM, cronoVM = cronosVM )
            }
        }
    }
}

