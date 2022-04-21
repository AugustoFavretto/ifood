package com.example.ifoodtest.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ifoodtest.common.viewmodel.UIAction

class Action<Action : UIAction> {

    private val _action: MutableLiveData<Action> = MutableLiveData()
    val action: LiveData<Action> = _action

    fun sendAction(action: () -> Action) {
        _action.value = action()
    }
}