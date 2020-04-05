package co.icanteach.app.coronatracker.domain.dashboard

import co.icanteach.app.coronatracker.core.Resource
import co.icanteach.app.coronatracker.core.inject.DefaultDispatcher
import co.icanteach.app.coronatracker.core.map
import co.icanteach.app.coronatracker.data.CoronaTrackerRepository
import co.icanteach.app.coronatracker.domain.DashboardItemMapper
import co.icanteach.app.coronatracker.domain.dashboard.model.DashboardItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchDashboardUseCase @Inject constructor(
    private val repository: CoronaTrackerRepository,
    private val mapper: DashboardItemMapper,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    @ExperimentalCoroutinesApi
    fun fetchGlobalStats(): Flow<Resource<List<DashboardItem>>> {
        return repository
            .fetchGlobalStats()
            .map { resource ->
                resource.map { response ->
                    mapper.mapFromResponse(response)
                }
            }.flowOn(dispatcher)
    }
}