package com.example.gitusers.ui.MainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gitusers.domain.model.Branch
import com.example.gitusers.domain.repository.RepoRepository

@Composable
fun RepoScreen(
    scaffoldState: ScaffoldState,
    viewModel: MainScreenViewModel
) {

    val branches = viewModel.branchesState.branches
    val commits = viewModel.commitsState.commits
    if (branches == null) {
        Text(text = "Error A")
    } else if (branches == listOf<Branch>()) {
        Text(text = "No branches here")
    } else if (commits == null) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(branches) { branch ->
                Text(
                    text = branch.name
                )
            }
        }
        Text(text = "No commits here")
    } else {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(branches) { branch ->
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = branch.name
                )
            }
            itemsIndexed(commits) { index, commit ->
                if (index < 10) {
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .padding(top = 10.dp)
                        ,
                        text = commit.message
                    )
                }
            }
        }

    }
}