package com.example.gitusers.ui.MainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gitusers.domain.model.Branch
import com.example.gitusers.domain.repository.RepoRepository
import com.example.gitusers.ui.theme.PalleteDarkBrown
import com.example.gitusers.ui.theme.PalleteLighterBrown
import com.example.gitusers.ui.theme.PalleteYellow

@Composable
fun RepoScreen(
    scaffoldState: ScaffoldState,
    viewModel: MainScreenViewModel
) {

    val branches = viewModel.branchesState.branches
    val commits = viewModel.commitsState.commits
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PalleteLighterBrown)
    ) {
        if (branches == null) {
            Text(text = "Error A")
        } else if (branches == listOf<Branch>()) {
            Text(text = "No branches here")
        } else if (commits == null) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(25.dp)
            ) {
                items(branches) { branch ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(PalleteYellow)
                    ) {
                        Text(
                            modifier = Modifier.padding(13.dp),
                            text = branch.name,
                            fontSize = 16.sp,
                            color = PalleteDarkBrown
                        )
                    }
                }
            }
            Text(text = "No commits here")
        } else {
            LazyColumn(
                Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                itemsIndexed(branches) { index, branch ->
                    if (index == 0) {
                        Text(
                            text = "Branches",
                            fontSize = 22.sp,
                            modifier = Modifier.padding(20.dp, 5.dp, 20.dp, 20.dp),
                            color = PalleteDarkBrown,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(PalleteYellow)
                        ) {
                            Text(
                                modifier = Modifier.padding(13.dp),
                                text = branch.name,
                                fontSize = 16.sp,
                                color = PalleteDarkBrown
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(PalleteYellow)
                        ) {
                            Text(
                                modifier = Modifier.padding(13.dp),
                                text = branch.name,
                                fontSize = 16.sp,
                                color = PalleteDarkBrown
                            )
                        }
                    }
                }
                itemsIndexed(commits) { index, commit ->
                    if (index == 0) {
                        Text(
                            text = "Last commits",
                            fontSize = 22.sp,
                            modifier = Modifier.padding(20.dp),
                            color = PalleteDarkBrown,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(PalleteYellow)
                        ) {
                            Text(
                                modifier = Modifier.padding(13.dp),
                                text = commit.message,
                                fontSize = 16.sp,
                                color = PalleteDarkBrown
                            )
                        }
                    }
                    else if (index < 10) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(PalleteYellow)
                        ) {
                            Text(
                                modifier = Modifier.padding(13.dp),
                                text = commit.message,
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