package com.example.jetpack9.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpack9.components.CronoCard
import com.example.jetpack9.components.FloatButton
import com.example.jetpack9.components.MainTitle
import com.example.jetpack9.components.formatTiempo
import com.example.jetpack9.viewModels.CronometroViewModel
import com.example.jetpack9.viewModels.CronosViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navC:NavController,cronosVM:CronosViewModel){
    Scaffold ( topBar = { CenterAlignedTopAppBar(title = { MainTitle(title = "Crono App.")},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors
            (containerColor = MaterialTheme.colorScheme.primary))},
        floatingActionButton = { FloatButton {
        navC.navigate("AddView")
    }}){
        ContentHomeView(it,navC,cronosVM)
    }
}

@Composable
fun ContentHomeView(it:PaddingValues,navC:NavController,cronosVM: CronosViewModel){
    Column (modifier = Modifier.padding(it)){
        val cronoslist by cronosVM.cronosList.collectAsState()
        LazyColumn {
            items(cronoslist){item ->
                val delete = SwipeAction(icon = rememberVectorPainter(Icons.Default.Delete ),
                    background = Color.Red, onSwipe = {cronosVM.deleteCrono(item)})
                SwipeableActionsBox (endActions = listOf(delete), swipeThreshold = 270.dp){
                    CronoCard(titulo = item.title, crono = formatTiempo(tiempo = item.crono)) {
                        navC.navigate("EditView/${item.id}")
                    }
                }

            }
        }
    }
}