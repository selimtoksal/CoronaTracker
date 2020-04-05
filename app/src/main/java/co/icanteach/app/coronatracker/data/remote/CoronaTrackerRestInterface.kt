package co.icanteach.app.coronatracker.data.remote

import co.icanteach.app.coronatracker.data.remote.model.GlobalStatsResponse
import retrofit2.http.GET

interface CoronaTrackerRestInterface {
    @GET("free-api?global=stats")
    suspend fun fetchGlobalStats(): GlobalStatsResponse
}