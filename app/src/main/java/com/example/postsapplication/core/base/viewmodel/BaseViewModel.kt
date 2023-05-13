package com.example.postsapplication.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Abstract class used to define Base View model
 */
abstract class BaseViewModel<STATE : ViewState?>(
    /**
     * instance of [ViewState], holds the initial state of the view
     */
    private val initialState: STATE,
) : ViewModel() {

    /**
     * instance of [ViewState], used to get the current state of the view
     */
    val currentState: STATE
        get() = uiState.value

    /**
     * state flow of [ViewState], emits the new state to the ui
     * [_uiState] is Mutable State Flow used by the view model to emit the new state
     * [uiState] is an Immutable State Flow used by the view to listen to state changes
     */
    private val _uiState: MutableStateFlow<STATE> by lazy {
        MutableStateFlow(initialState)
    }
    val uiState = _uiState.asStateFlow()

    /**
     * state flow of [Boolean], emits true or false for the progress loader visibility
     * [_loadingFlow] is Mutable State Flow used by the view model to emit new loading value
     * [loadingFlow] is an Immutable State Flow used by the view to listen to loading changes
     */
    private val _loadingFlow: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }
    val loadingFlow = _loadingFlow.asStateFlow()

    /**
     * function used to emit new state in [_uiState]
     *
     * @param state is the new state to be emitted to the view
     */
    suspend fun onViewState(state: STATE) {
        _uiState.emit(state)
    }

    /**
     * function used to emit new loading state in [_loadingFlow]
     *
     * @param showLoading is the new loading state to be emitted
     */
    fun setLoading(showLoading: Boolean) {
        viewModelScope.launch {
            _loadingFlow.emit(showLoading)
        }
    }
}