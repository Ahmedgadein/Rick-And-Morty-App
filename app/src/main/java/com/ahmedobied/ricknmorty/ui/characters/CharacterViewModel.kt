package com.ahmedobied.ricknmorty.ui.characters
import androidx.lifecycle.ViewModel
import com.ahmedobied.ricknmorty.data.repository.CharacterRepository
import com.ahmedobied.ricknmorty.internal.lazyDeffered

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {
    val character by lazyDeffered {
        repository.getCharacter(1)
    }

    val characters by lazyDeffered {
        repository.getAllCharacters()
    }
}