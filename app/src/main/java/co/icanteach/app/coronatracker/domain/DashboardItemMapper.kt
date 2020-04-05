package co.icanteach.app.coronatracker.domain

import co.icanteach.app.coronatracker.R
import co.icanteach.app.coronatracker.core.Mapper
import co.icanteach.app.coronatracker.data.remote.model.GlobalStatsResponse
import co.icanteach.app.coronatracker.domain.dashboard.model.DashboardItem
import javax.inject.Inject

class DashboardItemMapper @Inject constructor() : Mapper<GlobalStatsResponse, List<DashboardItem>> {

    override fun mapFromResponse(response: GlobalStatsResponse): List<DashboardItem> {

        val dashboardItems = mutableListOf<DashboardItem>()
        val globalStat = response.results.getOrNull(0)

        globalStat?.let {
            dashboardItems.add(
                0, DashboardItem(
                    caseTotal = globalStat.totalActiveCases.toString(),
                    caseDisplayName = R.string.total_active_cases
                )
            )

            dashboardItems.add(
                1, DashboardItem(
                    caseTotal = globalStat.totalRecovered.toString(),
                    caseDisplayName = R.string.total_recovered
                )
            )

            dashboardItems.add(
                2, DashboardItem(
                    caseTotal = globalStat.totalUnresolved.toString(),
                    caseDisplayName = R.string.total_unresolved
                )
            )

            dashboardItems.add(
                3, DashboardItem(
                    caseTotal = globalStat.totalDeaths.toString(),
                    caseDisplayName = R.string.total_deaths
                )
            )

            dashboardItems.add(
                4, DashboardItem(
                    caseTotal = globalStat.totalNewCasesToday.toString(),
                    caseDisplayName = R.string.total_new_cases_today
                )
            )

            dashboardItems.add(
                5, DashboardItem(
                    caseTotal = globalStat.totalNewDeathsToday.toString(),
                    caseDisplayName = R.string.total_new_deaths_today
                )
            )

            dashboardItems.add(
                6, DashboardItem(
                    caseTotal = globalStat.totalActiveCases.toString(),
                    caseDisplayName = R.string.total_active_cases
                )
            )

            dashboardItems.add(
                7, DashboardItem(
                    caseTotal = globalStat.totalSeriousCases.toString(),
                    caseDisplayName = R.string.total_serious_cases
                )
            )

            dashboardItems.add(
                8, DashboardItem(
                    caseTotal = globalStat.totalAffectedCountries.toString(),
                    caseDisplayName = R.string.total_affected_countries
                )
            )

        }
        return dashboardItems
    }

}