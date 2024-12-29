package com.yarendemirkaya.sample.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SampleAddPersonScreen(viewModel: SampleViewModel,navController: NavHostController) {
    val tfName = remember { mutableStateOf("") }
    val tfNumber = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = tfName.value,
            onValueChange = { tfName.value = it },
            label = { Text(text = "Name") })
        TextField(
            value = tfNumber.value,
            onValueChange = { tfNumber.value = it },
            label = { Text(text = "Number") })

        Button(onClick = {
            viewModel.savePerson(tfName.value, tfNumber.value.toInt())
            navController.navigate("home")
        }
        ) {
            Text(text = "Save")
        }
    }
    BackHandler {
        navController.navigate("home")
    }
}

