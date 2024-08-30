package com.example.jetpack9.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpack9.viewModels.CronometroViewModel
import com.example.jetpack9.viewModels.CronosViewModel
import com.example.jetpack9.views.AddView
import com.example.jetpack9.views.EditView
import com.example.jetpack9.views.HomeView

@Composable
fun NavManager(cronometroVM:CronometroViewModel,cronoVM:CronosViewModel){
    val navC = rememberNavController()
    NavHost(navController = navC, startDestination = "Home") {
        composable("Home"){
            HomeView(navC,cronoVM)
        }
        composable("AddView"){
            AddView(navC,cronometroVM,cronoVM)
        }
        composable("EditView/{id}", arguments = listOf(navArgument("id"){type = NavType.LongType})){
            val id = it.arguments?.getLong("id")
            EditView(navC,cronometroVM,cronoVM,id!!)
        }
    }
}