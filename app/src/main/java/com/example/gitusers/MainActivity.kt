package com.example.gitusers

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.gitusers.data.navigation.*
import com.example.gitusers.ui.MainScreen.MainScreen
import com.example.gitusers.ui.MainScreen.MainScreenViewModel
import com.example.gitusers.ui.MainScreen.RepoScreen
import com.example.gitusers.ui.theme.PalleteLighterBrown
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                ,
                scaffoldState = scaffoldState
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Route.MAIN
                ) {
                    composable(Route.MAIN) {
                        val viewModel: MainScreenViewModel = hiltViewModel(navController.previousBackStackEntry ?: it)
                        MainScreen(
                            scaffoldState = scaffoldState,
                            onNavigate = navController::navigate,
                            viewModel
                        )
                    }
                    composable(Route.REPO) {
                        val viewModel: MainScreenViewModel = hiltViewModel(navController.previousBackStackEntry ?: it)
                        RepoScreen(
                            scaffoldState = scaffoldState,
                            viewModel
                        )
                    }
                }
            }
        }
    }
}