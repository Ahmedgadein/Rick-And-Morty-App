package com.ahmedobied.ricknmorty.ui.characters

import android.util.Log
import androidx.lifecycle.*
import com.ahmedobied.ricknmorty.data.repository.CharacterRepository
import com.ahmedobied.ricknmorty.internal.lazyDeffered
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    val characters by lazyDeffered {
        repository.getAllCharacters()
    }

    fun fetchNextPage() {
        viewModelScope.launch {
            repository.fetchNextPage()
        }
    }
}