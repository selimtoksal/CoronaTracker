package co.icanteach.app.coronatracker.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icanteach.app.coronatracker.core.Resource
import co.icanteach.app.coronatracker.domain.dashboard.FetchDashboardUseCase
import co.icanteach.app.coronatracker.domain.dashboard.model.DashboardItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DashboardViewModel @Inject constructor(
    private val fetchDashboardUseCase: FetchDashboardUseCase
) : ViewModel() {

    private val dashboardItems = MutableLiveData<List<DashboardItem>>()

    fun getDashboardItems(): LiveData<List<DashboardItem>> = dashboardItems

    init {
        fetchDashboard()
    }

    private fun fetchDashboard() {
        fetchDashboardUseCase.fetchDashboard()
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> updatePageForResult(resource.data)
                    is Resource.Error -> updatePageForError(resource.exception)
                    is Resource.Loading -> updatePageForLoading()
                }
            }
            .catch { updatePageForError(it) }
            .launchIn(viewModelScope)
    }

    private fun updatePageForResult(data: List<DashboardItem>) {
        dashboardItems.value = data
    }

    private fun updatePageForError(throwable: Throwable) {

    }

    private fun updatePageForLoading() {

    }
}