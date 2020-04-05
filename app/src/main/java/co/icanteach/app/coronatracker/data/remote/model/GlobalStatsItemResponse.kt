package co.icanteach.app.coronatracker.data.remote.model

import com.squareup.moshi.Json


class GlobalStatsItemResponse constructor(
    @field:Json(name = "total_cases")
    val total_cases: Long,
    @field:Json(name = "total_recovered")
    val totalRecovered: Long,
    @field:Json(name = "total_unresolved")
    val totalUnresolved: Long,
    @field:Json(name = "total_deaths")
    val totalDeaths: Long,
    @field:Json(name = "total_new_cases_today")
    val totalNewCasesToday: Long,
    @field:Json(name = "total_new_deaths_today")
    val totalNewDeathsToday: Long,
    @field:Json(name = "total_active_cases")
    val totalActiveCases: Long,
    @field:Json(name = "total_serious_cases")
    val totalSeriousCases: Long,
    @field:Json(name = "total_affected_countries")
    val totalAffectedCountries: Long
)