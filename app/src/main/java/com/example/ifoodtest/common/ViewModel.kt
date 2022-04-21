package com.example.ifoodtest.common

import androidx.lifecycle.LiveData
import com.example.ifoodtest.common.viewmodel.UIAction
import com.example.ifoodtest.common.viewmodel.UIState

abstract class ViewModel<State : UIState, Action : UIAction>(
    initialState: State
) : androidx.lifecycle.ViewModel() {

    private val viewModelState = State(initialState)
    private val viewModelAction = Action<Action>()

    val state: LiveData<State> = viewModelState.state

    val action: LiveData<Action> = viewModelAction.action

    protected fun setState(state: (State) -> State) {
        viewModelState.setState(state)
    }

    protected open fun sendAction(action: () -> Action) {
        viewModelAction.sendAction(action)
    }
}
