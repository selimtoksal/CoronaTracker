package co.icanteach.app.coronatracker.core.inject

import co.icanteach.app.coronatracker.data.remote.NetworkModule
import co.icanteach.app.coronatracker.presentation.dashboard.inject.DashboardComponent
import co.icanteach.app.coronatracker.presentation.dashboard.inject.DashboardModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        CoroutinesModule::class,
        NetworkModule::class,
        DashboardModule::class]
)
interface ApplicationComponent {
    val dashboardComponent: DashboardComponent.Factory
}