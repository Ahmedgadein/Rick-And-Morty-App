import com.ahmedobied.ricknmorty.data.db.dao.LastFetchDao
import com.ahmedobied.ricknmorty.data.db.entities.LastFetchEntity
import com.ahmedobied.ricknmorty.data.repository.location.LocationRepository
import org.threeten.bp.ZonedDateTime
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ahmedobied.ricknmorty.data.db.dao.LocationDao
import com.ahmedobied.ricknmorty.data.db.entities.LocationEntity
import com.ahmedobied.ricknmorty.data.mapper.LocationMapper
import com.ahmedobied.ricknmorty.data.network.datasource.location.LocationNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.models.MultipleLocationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocationRepositoryImpl(
    private val locationNetworkDataSource: LocationNetworkDataSource,
    private val locationDao: LocationDao,
    private val lastFetchDao: LastFetchDao
) : LocationRepository {
    private var nextPage = 2

    init {
        locationNetworkDataSource.downloadedLocations.observeForever(Observer {
            if (it != null)
                persistLocations(it)
        })

        lastFetchDao.getNextPage().observeForever(Observer {
            if(it!= null)
                nextPage = it
        })
    }

    private fun persistLocations(locations: MultipleLocationResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val locationEntities = locations.results.map {
                LocationMapper.locationResponseToEntity(it)
            }
            locationDao.insert(locationEntities)
            lastFetchDao.insert(
                LastFetchEntity(
                    nextPage = nextPage,
                    lastFetchTime = ZonedDateTime.now()
                )
            )

        }
    }

    override suspend fun getAllLocations(): LiveData<List<LocationEntity>> {
        return withContext(Dispatchers.IO){
            initLocations()
            return@withContext locationDao.getAllLocations()
        }
    }

    private suspend fun initLocations(){
        GlobalScope.launch(Dispatchers.IO) {
            val shouldFetch = shouldFetch()
            if(shouldFetch)
                fetchLocations()
        }
    }
    private suspend fun shouldFetch():Boolean{
        return withContext(Dispatchers.IO){
            val lastFetched = lastFetchDao.getLastFetch()?.lastFetchTime
            val oneDayAgo = ZonedDateTime.now().minusDays(1)
            return@withContext lastFetched?.isBefore(oneDayAgo) ?: true
        }

    }

    private suspend fun fetchLocations(page:Int = 1){
        GlobalScope.launch(Dispatchers.IO) {
            locationNetworkDataSource.fetchLocation(page = page)
        }
    }
}