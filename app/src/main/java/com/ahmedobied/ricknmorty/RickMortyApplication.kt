package com.ahmedobied.ricknmorty

import android.app.Application
import com.ahmedobied.ricknmorty.data.db.RickMortyDatabase
import com.ahmedobied.ricknmorty.data.network.CharacterNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.CharacterNetworkDataSourceImpl
import com.ahmedobied.ricknmorty.data.network.RickAndMortyApiService
import com.ahmedobied.ricknmorty.data.repository.CharacterRepository
import com.ahmedobied.ricknmorty.data.repository.CharacterRepositoryImpl
import com.ahmedobied.ricknmorty.ui.characters.CharacterViewModelFactory
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

class RickMortyApplication : Application(), DIAware {
    override val di by DI.lazy {
        import(androidXModule(this@RickMortyApplication))
        bind() from  singleton { RickMortyDatabase(instance()) }
        bind() from singleton { instance<RickMortyDatabase>().getCharacterDao() }
        bind() from singleton { RickAndMortyApiService() }
        bind<CharacterNetworkDataSource>() with singleton { CharacterNetworkDataSourceImpl(instance()) }
        bind<CharacterRepository>() with singleton { CharacterRepositoryImpl(instance(), instance()) }
        bind() from provider { CharacterViewModelFactory(instance()) }
    }
}