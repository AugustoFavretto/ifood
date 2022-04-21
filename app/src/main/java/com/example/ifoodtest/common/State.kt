package com.example.ifoodtest.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ifoodtest.common.viewmodel.UIState

class State<State : UIState>(initialState: State) {

    private val _state = MutableLiveData(initialState)
    val state: LiveData<State> = _state

    fun setState(state: (State) -> State) {
        // As we always set a initial state and setter is private and
        // always set a non null value, value can never be null.
        _state.value = state(_state.value!!)
    }
}