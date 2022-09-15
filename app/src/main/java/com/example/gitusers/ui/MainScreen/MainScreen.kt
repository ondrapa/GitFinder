package com.example.gitusers.ui.MainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gitusers.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun MainScreen(
    scaffoldState: ScaffoldState,
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val repos = viewModel.reposState.repos
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        TextField(
            value = viewModel.userToFind,
            onValueChange = {
                viewModel.onUserToFindChange(it)
            }
        )
        Text(
            text = "refresh",
            fontSize = 20.sp,
            modifier = Modifier.clickable { viewModel.loadRepo() }
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (repos == null) {
            Text(text = "SelectSomeone")
        } else {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                items(repos) { repo ->
                    Text(text = repo.name)
                }
            }
        }
    }
}