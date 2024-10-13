package com.example.navigationproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.TopAppBar
import androidx.navigation.NavController



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                ScaffoldApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text("My App") },
        navigationIcon = {
            IconButton(onClick = { /* TODO: Navigation logic */ }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = { navController.navigate("Info") }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = { navController.navigate("Settings") }
                )
            }
        }
    )
}

@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable(route = "Home") {
            Mainscreen(navController)
        }
        composable(route = "Info") {
            InfoScreen(navController)
        }
        composable(route = "Settings") {
            SettingsScreen(navController)
        }
    }
}

@Composable
fun Mainscreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(navController) },
        content = { paddingValues ->
            Text(
                text = "Content for Home Screen",
                modifier = Modifier.padding(paddingValues) // Add padding to content
            )
        },
    )
}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(navController) },
        content = { paddingValues ->
            Text(
                text = "Content for Info Screen",
                modifier = Modifier.padding(paddingValues) // Add padding to content
            )
        },
    )
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(navController) },
        content = { paddingValues ->
            Text(
                text = "Content for Settings Screen",
                modifier = Modifier.padding(paddingValues) // Add padding to content
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewScaffoldApp() {
    ScaffoldApp()
}
