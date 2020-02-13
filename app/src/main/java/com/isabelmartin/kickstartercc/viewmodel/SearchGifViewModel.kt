package com.isabelmartin.kickstartercc.viewmodel

import androidx.lifecycle.ViewModel
import com.isabelmartin.kickstartercc.repository.RemoteRepository

object SearchGifViewModel : ViewModel() {

    init {
        var list = RemoteRepository().search("Pikachu")
    }
    override fun onCleared() {
        // TODO: clear here all the possible subscriptions
        super.onCleared()
    }
}