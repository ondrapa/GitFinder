package com.example.gitusers.ui.MainScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitusers.data.navigation.Route
import com.example.gitusers.domain.model.Branch
import com.example.gitusers.domain.repository.RepoRepository
import com.example.gitusers.ui.state.BranchesState
import com.example.gitusers.ui.state.CommitState
import com.example.gitusers.ui.state.ReposState
import com.example.gitusers.util.Resource
import com.example.gitusers.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: RepoRepository
): ViewModel() {

    var userToFind by mutableStateOf("")
        private set

    var reposState by mutableStateOf(ReposState())
        private set

    var branchesState by mutableStateOf(BranchesState(null))
        private set

    var commitsState by mutableStateOf(CommitState(null))
        private set

    fun onUserToFindChange(username: String) {
        userToFind = username
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun loadRepo() {
        if(userToFind == "") {
            sendUiEvent(UiEvent.ShowSnackbar(
                message = "Username can not be empty"
            ))
        } else {
            viewModelScope.launch {
                reposState = reposState.copy(
                    isLoading = true,
                    error = null
                )
                when (val result = repository.getRepos(userToFind)) {
                    is Resource.Success -> {
                        reposState = reposState.copy(
                            repos = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        reposState = reposState.copy(
                            repos = null,
                            isLoading = false,
                            error = result.message
                        )
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = result.message!!
                            )
                        )
                    }
                }
            }
        }
    }

    fun onRepoClick(repoName: String) {
        viewModelScope.launch {
            when (val result = repository.getBranches(userToFind, repoName)) {
                is Resource.Success -> {
                    branchesState = branchesState.copy(
                        branches = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    branchesState = branchesState.copy(
                        branches = null,
                        isLoading = false,
                        error = result.message
                    )
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            message = result.message ?: "An unknown error occurred"
                        )
                    )
                }
            }
        }
        loadCommits(repoName)
        sendUiEvent(UiEvent.Navigate(Route.REPO))
    }

    fun loadCommits(repoName: String) {
        viewModelScope.launch {
            when (val result = repository.getCommits(userToFind, repoName)) {
                is Resource.Success -> {
                    commitsState = commitsState.copy(
                        commits = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    commitsState = commitsState.copy(
                        commits = null,
                        isLoading = false,
                        error = result.message
                    )
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            message = result.message ?: "An unknown error occurred"
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}