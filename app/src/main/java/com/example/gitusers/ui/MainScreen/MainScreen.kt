package com.example.gitusers.ui.MainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gitusers.ui.theme.PalleteDarkBrown
import com.example.gitusers.ui.theme.PalleteLightBrown
import com.example.gitusers.ui.theme.PalleteLighterBrown
import com.example.gitusers.ui.theme.PalleteYellow
import com.example.gitusers.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun MainScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainScreenViewModel
) {

    val repos = viewModel.reposState.repos
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(PalleteLighterBrown)
    ) {
        FloatingActionButton(
            onClick = { viewModel.onInfoClick() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(15.dp),
            backgroundColor = PalleteDarkBrown
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                modifier = Modifier.size(50.dp),
                tint = PalleteYellow
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp)

        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 65.dp)
                    .padding(end = 20.dp),
                value = viewModel.userToFind,
                singleLine = true,
                onValueChange = {
                    viewModel.onUserToFindChange(it)
                },
                textStyle = TextStyle.Default.copy(fontSize = 20.sp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = PalleteLightBrown,
                    backgroundColor = PalleteLighterBrown,
                    cursorColor = PalleteLightBrown,
                    focusedIndicatorColor = PalleteLightBrown,
                    unfocusedIndicatorColor = PalleteDarkBrown
                )
            )
            Box(
            ) {
                Text(
                    text = "Find",
                    color = PalleteLightBrown,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .padding(end = 20.dp)
                        .clickable { viewModel.loadRepo() }
                )
            }
            if (repos == null) {

            } else {
                LazyColumn(
                    Modifier
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(repos) { repo ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(PalleteYellow)
                                .clickable { viewModel.onRepoClick(repo.name) }
                        ) {
                            Text(
                                modifier = Modifier.padding(13.dp),
                                text = repo.name,
                                fontSize = 16.sp,
                                color = PalleteDarkBrown
                            )
                        }
                    }
                }
            }
        }
    }
}