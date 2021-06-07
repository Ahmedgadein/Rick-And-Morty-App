package com.ahmedobied.ricknmorty

import android.app.Application
import com.ahmedobied.ricknmorty.data.db.RickMortyDatabase
import com.ahmedobied.ricknmorty.data.network.common.ConnectivityInterceptor
import com.ahmedobied.ricknmorty.data.network.common.ConnectivityInterceptorImpl
import com.ahmedobied.ricknmorty.data.network.common.RickAndMortyApiService
import com.ahmedobied.ricknmorty.data.network.datasource.character.CharacterNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.datasource.character.CharacterNetworkDataSourceImpl
import com.ahmedobied.ricknmorty.data.repository.character.CharacterRepository
import com.ahmedobied.ricknmorty.data.repository.character.CharacterRepositoryImpl
import com.ahmedobied.ricknmorty.ui.characters.CharacterViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

class RickMortyApplication : Application(), DIAware {
    override val di by DI.lazy {
        import(androidXModule(this@RickMortyApplication))
        bind() from  singleton { RickMortyDatabase(instance()) }
        bind() from singleton { instance<RickMortyDatabase>().getCharacterDao() }
        bind() from singleton { instance<RickMortyDatabase>().getLastFetchDao() }
        bind<ConnectivityInterceptor>() with singleton {
            ConnectivityInterceptorImpl(
                instance()
            )
        }
        bind() from singleton {
            RickAndMortyApiService(
                instance()
            )
        }
        bind<CharacterNetworkDataSource>() with singleton {
            CharacterNetworkDataSourceImpl(
                instance()
            )
        }
        bind<CharacterRepository>() with singleton {
            CharacterRepositoryImpl(
                instance(),
                instance(),
                instance()
            )
        }
        bind() from provider { CharacterViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}