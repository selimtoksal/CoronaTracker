package co.icanteach.app.coronatracker.data

import co.icanteach.app.coronatracker.core.Resource
import co.icanteach.app.coronatracker.data.remote.model.GlobalStatsResponse
import co.icanteach.app.coronatracker.data.remote.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoronaTrackerRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun fetchGlobalStats(): Flow<Resource<GlobalStatsResponse>> {
        return flow {
            emit(Resource.Loading)
            try {
                val globalStatsResponse = remoteDataSource.fetchGlobalStats()
                emit(Resource.Success(globalStatsResponse))
            } catch (exception: Exception) {
                emit(Resource.Error(exception))
            }
        }
    }
}