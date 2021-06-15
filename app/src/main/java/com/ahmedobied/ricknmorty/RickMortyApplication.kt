package com.ahmedobied.ricknmorty

import LocationRepositoryImpl
import android.app.Application
import com.ahmedobied.ricknmorty.data.db.RickMortyDatabase
import com.ahmedobied.ricknmorty.data.network.common.ConnectivityInterceptor
import com.ahmedobied.ricknmorty.data.network.common.ConnectivityInterceptorImpl
import com.ahmedobied.ricknmorty.data.network.common.RickAndMortyApiService
import com.ahmedobied.ricknmorty.data.network.datasource.character.CharacterNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.datasource.character.CharacterNetworkDataSourceImpl
import com.ahmedobied.ricknmorty.data.network.datasource.episode.EpisodeNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.datasource.episode.EpisodeNetworkDataSourceImpl
import com.ahmedobied.ricknmorty.data.network.datasource.location.LocationNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.datasource.location.LocationNetworkDataSourceImpl
import com.ahmedobied.ricknmorty.data.repository.character.CharacterRepository
import com.ahmedobied.ricknmorty.data.repository.character.CharacterRepositoryImpl
import com.ahmedobied.ricknmorty.data.repository.episode.EpisodeRepository
import com.ahmedobied.ricknmorty.data.repository.episode.EpisodeRepositoryImpl
import com.ahmedobied.ricknmorty.data.repository.location.LocationRepository
import com.ahmedobied.ricknmorty.ui.characters.CharacterViewModelFactory
import com.ahmedobied.ricknmorty.ui.episodes.EpisodeViewModelFactory
import com.ahmedobied.ricknmorty.ui.locations.LocationViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

class RickMortyApplication : Application(), DIAware {
    override val di by DI.lazy {
        import(androidXModule(this@RickMortyApplication))
        bind() from singleton { RickMortyDatabase(instance()) }
        bind() from singleton { instance<RickMortyDatabase>().getCharacterDao() }
        bind() from singleton { instance<RickMortyDatabase>().getLastFetchDao() }
        bind() from singleton { instance<RickMortyDatabase>().getLocationDao() }
        bind() from singleton { instance<RickMortyDatabase>().getEpisodeDao() }
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
        bind<LocationNetworkDataSource>() with singleton {
            LocationNetworkDataSourceImpl(
                instance()
            )
        }

        bind<EpisodeNetworkDataSource>() with singleton {
            EpisodeNetworkDataSourceImpl(
                instance()
            )
        }

        bind<CharacterRepository>() with singleton {
            CharacterRepositoryImpl(instance(), instance())
        }
        bind<LocationRepository>() with singleton {
            LocationRepositoryImpl(instance(), instance())
        }
        bind<EpisodeRepository>() with singleton { EpisodeRepositoryImpl(instance(), instance()) }

        bind() from provider { CharacterViewModelFactory(instance()) }
        bind() from provider { LocationViewModelFactory(instance()) }
        bind() from provider { EpisodeViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}