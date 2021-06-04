package com.ahmedobied.ricknmorty.data.repository.character

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
import com.ahmedobied.ricknmorty.internal.getPage
import kotlinx.coroutines.*
import org.threeten.bp.ZonedDateTime

class CharacterRepositoryImpl(
    private val characterNetworkDataSource: CharacterNetworkDataSource,
    private val characterDao: CharacterDao,
    private val lastFetchDao: LastFetchDao
) :
    CharacterRepository {
    private var nextPage: Int = 2

    init {
        characterNetworkDataSource.downloadedCharacters.observeForever(Observer {
            if (it != null) persistCharacters(it)
        })

        lastFetchDao.getNextPage().observeForever(Observer {
            if (it != null) nextPage = it
        })
    }


    private fun persistCharacters(charactersResponse: MultipleCharacterResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val characters = charactersResponse.characters.map {
                CharacterMapper.characterResponseToEntity(it)
            }
            val nextPage = getPage(charactersResponse.info.next) ?: 2
            characterDao.insert(characters)
            lastFetchDao.insert(
                LastFetchEntity(
                    lastFetchTime = ZonedDateTime.now(),
                    nextPage = nextPage
                )
            )
        }
    }

    override suspend fun getAllCharacters(): LiveData<List<CharacterEntity>> {
        return withContext(Dispatchers.IO) {
            initCharacters()
            return@withContext characterDao.getAllCharacters()
        }
    }

    private suspend fun initCharacters() {
        val shouldFetch = shouldFetch()
        if (shouldFetch)
            fetchCharacters()
        Log.i("FetchNeeded", "Fetched From Network: $shouldFetch")
    }

    private suspend fun shouldFetch(): Boolean {
        return withContext(Dispatchers.IO) {
            val dayAgo = ZonedDateTime.now().minusDays(1)
            val lastFetched = lastFetchDao.getLastFetch() ?: return@withContext true
            val lastFetchTime = lastFetched.lastFetchTime
            return@withContext lastFetchTime.isBefore(dayAgo)
        }
    }

    override suspend fun fetchNextPage() {
        fetchCharacters(nextPage)
    }

    private suspend fun fetchCharacters(page: Int = 1) {
        GlobalScope.launch(Dispatchers.IO) {
            characterNetworkDataSource.fetchCharacters(page)
        }
    }
}