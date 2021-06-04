package com.ahmedobied.ricknmorty.ui.characters

import androidx.lifecycle.*
import com.ahmedobied.ricknmorty.data.repository.character.CharacterRepository
import com.ahmedobied.ricknmorty.internal.lazyDeferred
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    val characters by lazyDeferred {
        repository.getAllCharacters()
    }

    fun fetchNextPage() {
        viewModelScope.launch {
            repository.fetchNextPage()
        }
    }
}