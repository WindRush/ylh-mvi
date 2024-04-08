package com.ylh.mvi.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author: yangLiHai
 * @time: 2024/03/05
 * @desc:
 */
abstract class BaseViewModel<S: IUIState, I: IUIIntent>: ViewModel() {
    private val _uiStateFlow = MutableStateFlow(initUIState())
    private val _uiIntentFlow: Channel<I> = Channel()
    val uiStateFlow: StateFlow<S> = _uiStateFlow
    val uiIntentFlow: Flow<I> = _uiIntentFlow.receiveAsFlow()
    protected abstract fun initUIState(): S
    fun sendUIState(copy: S.() -> S) {
        _uiStateFlow.update {
            copy(_uiStateFlow.value)
        }
    }
    fun sendUIIntent(i: I) {
        viewModelScope.launch {
            _uiIntentFlow.send(i)
        }
    }
    init {
        viewModelScope.launch {
            uiIntentFlow.collect {
                handleIntent(it)
            }
        }
    }

    protected abstract fun handleIntent(i: I)
}