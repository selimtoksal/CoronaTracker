package co.icanteach.app.coronatracker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import co.icanteach.app.coronatracker.core.Resource
import co.icanteach.app.coronatracker.domain.dashboard.FetchDashboardUseCase
import co.icanteach.app.coronatracker.domain.dashboard.model.DashboardItem
import co.icanteach.app.coronatracker.presentation.dashboard.DashboardViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class DashboardViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Captor
    private lateinit var captor: ArgumentCaptor<List<DashboardItem>>

    @Mock
    lateinit var fetchDashboardUseCase: FetchDashboardUseCase

    @Mock
    lateinit var mockedObserver: Observer<List<DashboardItem>>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given success flow with mocked response, when view model initialized, then should dashboardItems livedata update with correct data`() = runBlockingTest {

        // Given
        val dashboardItem =
            DashboardItem(caseTotal = "943066", caseDisplayName = R.string.total_deaths)
        val dashboardItems = listOf(dashboardItem)
        val globalStatsFlow = flow {
            emit(Resource.Success(dashboardItems))
        }
        `when`(fetchDashboardUseCase.fetchGlobalStats()).thenReturn(globalStatsFlow)
        val dashboardViewModel = DashboardViewModel(
            fetchDashboardUseCase = fetchDashboardUseCase
        )
        dashboardViewModel.getDashboardItems().observeForever(mockedObserver)

        // Then
        verify(mockedObserver).onChanged(captor.capture())
        Truth.assertThat(captor.value[0].caseTotal).isEqualTo(dashboardItem.caseTotal)
    }
}