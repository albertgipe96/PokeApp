package com.example.pokeapp.common.ui.state

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface ViewStateDelegate<UiState> {
    val uiState: Flow<UiState>
    val stateValue: UiState
    suspend fun ViewStateDelegate<UiState>.reduce(action: (state: UiState) -> UiState)
    fun CoroutineScope.asyncReduce(action: (state: UiState) -> UiState)
}

class ViewStateDelegateImpl<UiState>(
    initialUiState: UiState
) : ViewStateDelegate<UiState> {

    private val  stateFlow = MutableStateFlow(initialUiState)

    override val uiState: Flow<UiState>
        get() = stateFlow.asStateFlow()

    override val stateValue: UiState
        get() = stateFlow.value

    private val mutex = Mutex()

    override suspend fun ViewStateDelegate<UiState>.reduce(action: (state: UiState) -> UiState) {
        mutex.withLock { stateFlow.emit(action(stateValue)) }
    }

    override fun CoroutineScope.asyncReduce(action: (state: UiState) -> UiState) {
        launch { reduce(action) }
    }

}

@Composable
fun <R> ViewStateDelegate<R>.collectWithLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
) = this.uiState.collectAsStateWithLifecycle(
    initialValue = this.stateValue,
    minActiveState = minActiveState
)