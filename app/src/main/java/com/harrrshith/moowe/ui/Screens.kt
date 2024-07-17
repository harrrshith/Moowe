package com.harrrshith.moowe.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScreenOne(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Screen One", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun ScreenTwo(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Screen Two", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun ScreenThree(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Screen Three", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun ScreenFour(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Screen Four", style = MaterialTheme.typography.titleLarge)
    }
}
