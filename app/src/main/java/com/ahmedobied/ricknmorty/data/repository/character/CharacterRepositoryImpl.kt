package com.ahmedobied.ricknmorty.data.repository.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ahmedobied.ricknmorty.data.db.dao.CharacterDao
import com.ahmedobied.ricknmorty.data.db.dao.LastFetchDao
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity
import com.ahmedobied.ricknmorty.data.db.entities.LastFetchEntity
import com.ahmedobied.ricknmorty.data.mapper.CharacterMapper
import com.ahmedobied.ricknmorty.data.network.datasource.character.CharacterNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.models.MultipleCharacterResponse
import com.ahmedobied.ricknmorty.internal.getPage
import kotlinx.coroutines.*
import org.threeten.bp.ZonedDateTime

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
            val nextPage = getPage(charactersResponse.info.next) ?: 2
            characterDao.insert(characters)
        }
    }

    override suspend fun getAllCharacters(): LiveData<List<CharacterEntity>> {
        return withContext(Dispatchers.IO) {
            initCharacters()
            return@withContext characterDao.getAllCharacters()
        }
    }

    private suspend fun initCharacters() {
        fetchCharacters()
    }


    override suspend fun fetchNextPage() {
//        fetchCharacters(nextPage)
    }

    private suspend fun fetchCharacters(page: Int = 1) {
        GlobalScope.launch(Dispatchers.IO) {
            characterNetworkDataSource.fetchCharacters(page)
        }
    }
}