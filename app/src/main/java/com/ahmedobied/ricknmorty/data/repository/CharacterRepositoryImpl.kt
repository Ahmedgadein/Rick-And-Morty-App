package com.ahmedobied.ricknmorty.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ahmedobied.ricknmorty.data.db.dao.CharacterDao
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity
import com.ahmedobied.ricknmorty.data.mapper.CharacterMapper
import com.ahmedobied.ricknmorty.data.network.CharacterNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.models.CharacterResponse
import com.ahmedobied.ricknmorty.data.network.models.MultipleCharacterResponse
import kotlinx.coroutines.*

class CharacterRepositoryImpl(
    private val characterNetworkDataSource: CharacterNetworkDataSource,
    private val characterDao: CharacterDao
) :
    CharacterRepository {
    init {
        characterNetworkDataSource.downloadedCharacters.observeForever(Observer {
            if (it != null) persistCharacters(it)
        })
    }

    private fun persistCharacters(charactersResponse: MultipleCharacterResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val characters = charactersResponse.characters.map {
                CharacterMapper.characterResponseToEntity(it)
            }
            characterDao.insert(characters)
        }
    }

    override suspend fun getCharacter(id: Int):LiveData<CharacterEntity> {
//        fetchCharacter(id)
        return withContext(Dispatchers.IO){
            return@withContext characterDao.getCharacter(id)
        }
    }

    override suspend fun getAllCharacters(page: Int): LiveData<List<CharacterEntity>> {
        fetchCharacters(page)
        return withContext(Dispatchers.IO){
            return@withContext characterDao.getAllCharacters()
        }
    }

    private fun fetchCharacters(page: Int = 1){
        GlobalScope.launch(Dispatchers.IO) {
            characterNetworkDataSource.fetchCharacters(page)
        }
    }
//    private fun fetchCharacter(id: Int){
//        GlobalScope.launch(Dispatchers.IO){
//            characterNetworkDataSource.fetchCharacter(id)
//        }
//    }
}