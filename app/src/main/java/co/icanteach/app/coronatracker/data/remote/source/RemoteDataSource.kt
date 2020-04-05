package co.icanteach.app.coronatracker.data.remote.source

import co.icanteach.app.coronatracker.data.remote.CoronaTrackerRestInterface
import co.icanteach.app.coronatracker.data.remote.model.GlobalStatsResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val restInterface: CoronaTrackerRestInterface
) {
    suspend fun fetchGlobalStats(): GlobalStatsResponse {
        return restInterface.fetchGlobalStats()
    }
}