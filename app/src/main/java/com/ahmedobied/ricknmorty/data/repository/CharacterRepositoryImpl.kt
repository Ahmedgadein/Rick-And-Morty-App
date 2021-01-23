package com.ahmedobied.ricknmorty.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ahmedobied.ricknmorty.data.db.dao.CharacterDao
import com.ahmedobied.ricknmorty.data.db.dao.LastFetchDao
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity
import com.ahmedobied.ricknmorty.data.db.entities.LastFetchEntity
import com.ahmedobied.ricknmorty.data.mapper.CharacterMapper
import com.ahmedobied.ricknmorty.data.network.CharacterNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.models.MultipleCharacterResponse
import kotlinx.coroutines.*
import org.threeten.bp.ZonedDateTime

class CharacterRepositoryImpl(
    private val characterNetworkDataSource: CharacterNetworkDataSource,
    private val characterDao: CharacterDao,
    private val lastFetchDao: LastFetchDao
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
            lastFetchDao.insert(LastFetchEntity(lastFetch = ZonedDateTime.now()))
        }
    }

    override suspend fun getCharacter(id: Int): LiveData<CharacterEntity> {
//        fetchCharacter(id)
        return withContext(Dispatchers.IO) {
            return@withContext characterDao.getCharacter(id)
        }
    }

    override suspend fun getAllCharacters(page: Int): LiveData<List<CharacterEntity>> {
            return withContext(Dispatchers.IO) {
                initCharacters(page)
                return@withContext characterDao.getAllCharacters()
            }
    }

    private suspend fun initCharacters(page: Int){
        if(shouldFetch())
            fetchCharacters(page)
        Log.i("FetchNeeded", "Fetched: ${shouldFetch()}")
    }

    private suspend fun shouldFetch(): Boolean {
        return withContext(Dispatchers.IO) {
            val dayAgo =  ZonedDateTime.now().minusDays(1)
            val lastFetched = lastFetchDao.getLastFetch()
            if (lastFetched == null) return@withContext true else return@withContext lastFetched.lastFetch.isBefore(dayAgo)

        }
    }

    private suspend fun fetchCharacters(page: Int = 1) {
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