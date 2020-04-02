package co.icanteach.app.coronatracker.domain.dashboard

import co.icanteach.app.coronatracker.core.Resource
import co.icanteach.app.coronatracker.core.combine
import co.icanteach.app.coronatracker.core.inject.DefaultDispatcher
import co.icanteach.app.coronatracker.data.CoronaTrackerRepository
import co.icanteach.app.coronatracker.domain.DashboardItemMapper
import co.icanteach.app.coronatracker.domain.dashboard.model.DashboardItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchDashboardUseCase @Inject constructor(
    private val repository: CoronaTrackerRepository,
    private val mapper: DashboardItemMapper,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    @ExperimentalCoroutinesApi
    fun fetchDashboard(): Flow<Resource<List<DashboardItem>>> {
        val totalDataFlow = repository.fetchTotalData()
        val countriesDataFlow = repository.fetchCountriesData()
        return totalDataFlow.combine(countriesDataFlow) { totalDataResource, countriesDataResource ->
            totalDataResource.combine(countriesDataResource) { totalData, countriesData ->
                mapper.mapFromResponse(totalData, countriesData)
            }
        }.flowOn(dispatcher)
    }
}