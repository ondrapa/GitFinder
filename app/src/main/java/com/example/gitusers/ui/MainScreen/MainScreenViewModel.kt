package com.example.gitusers.ui.MainScreen

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitusers.domain.repository.RepoRepository
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

    var reposState by mutableStateOf(RepoState())
        private set

    fun onUserToFindChange(username: String) {
        userToFind = username
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun loadRepo() {
        viewModelScope.launch {
            if(userToFind == "") {
                sendUiEvent(UiEvent.ShowSnackbar(
                    message = "Username can not be empty"
                ))
            } else {
                reposState = reposState.copy(
                    isLoading = true,
                    error = null
                )
                when (val result = repository.getRepo(userToFind)) {
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

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}