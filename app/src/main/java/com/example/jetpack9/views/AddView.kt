package com.example.jetpack9.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpack9.R
import com.example.jetpack9.components.CircleButton
import com.example.jetpack9.components.MainIconButton
import com.example.jetpack9.components.MainTextField
import com.example.jetpack9.components.MainTitle
import com.example.jetpack9.components.formatTiempo
import com.example.jetpack9.model.Cronos
import com.example.jetpack9.viewModels.CronometroViewModel
import com.example.jetpack9.viewModels.CronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navC:NavController,cronometroVM: CronometroViewModel,cronosVM:CronosViewModel){
    /*topBar = CenterAlignedTopAppBar(title = { MainTitle(title = "Add Crono.")}
        ,colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary)
        , navigationIcon = {
            MainIconButton(icon = Icons.Filled.ArrowBack, onClick = {

            })
        }

     */
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { MainTitle(title = "Add Crono.")},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary), navigationIcon = { MainIconButton(
            icon = Icons.Default.ArrowBack, onClick = {navC.popBackStack()})})
    }){
        ContentAddView(it,navC,cronometroVM,cronosVM)
    }
}

@Composable
fun ContentAddView(it:PaddingValues,navC: NavController,cronometroVM: CronometroViewModel,cronosVM: CronosViewModel){
    val state = cronometroVM.state
    LaunchedEffect(state.cronometroActivo) {
        cronometroVM.cronome()
    }
    Column (modifier = Modifier
        .padding(it)
        .padding(top = 30.dp)
        .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = formatTiempo(tiempo = cronometroVM.tiempo), fontSize = 50.sp, fontWeight = FontWeight.Bold)
        Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(vertical = 16.dp)){
            CircleButton(icon = painterResource(id = R.drawable.play), enabled = !state.cronometroActivo) {
                cronometroVM.iniciar()
            }
            CircleButton(icon = painterResource(id = R.drawable.pausa), enabled = state.cronometroActivo) {
                cronometroVM.pausar()
            }
            CircleButton(icon = painterResource(id = R.drawable.stop), enabled = !state.cronometroActivo) {
                cronometroVM.detener()
            }
            CircleButton(icon = painterResource(id = R.drawable.save), enabled = state.showSaveButton) {
                cronometroVM.showTextField()
            }
        }

        if(state.showTextField){
            MainTextField(value = state.title, onValueChange = {cronometroVM.onValue(it)}, label = "Inserte Titulo Aqui.")
            Button(onClick = {
                cronosVM.addCrono(
                    Cronos(
                        title = state.title,
                        crono = cronometroVM.tiempo
                    )
                )
                cronometroVM.detener()
                navC.popBackStack()
            }) {
                Text(text = "Guardar.")
            }
        }
        DisposableEffect(Unit) {
            onDispose {
                cronometroVM.detener()
            }
        }

    }
}

