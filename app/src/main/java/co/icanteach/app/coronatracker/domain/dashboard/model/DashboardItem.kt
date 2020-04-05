package co.icanteach.app.coronatracker.domain.dashboard.model

import androidx.annotation.StringRes

class DashboardItem(
    val caseTotal: String,
    @StringRes val caseDisplayName: Int
)