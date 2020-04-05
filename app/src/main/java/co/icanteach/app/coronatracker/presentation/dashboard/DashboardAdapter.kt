package co.icanteach.app.coronatracker.presentation.dashboard

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.icanteach.app.coronatracker.R
import co.icanteach.app.coronatracker.core.inflate
import co.icanteach.app.coronatracker.databinding.ItemDashboardBinding
import co.icanteach.app.coronatracker.domain.dashboard.model.DashboardItem
import javax.inject.Inject

class DashboardAdapter @Inject constructor() :
    RecyclerView.Adapter<DashboardAdapter.ItemViewHolder>() {

    private var dashboardList: MutableList<DashboardItem> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val itemBinding = parent.inflate<ItemDashboardBinding>(R.layout.item_dashboard, false)
        return ItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = dashboardList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getDashboardItem(position))
    }

    private fun getDashboardItem(position: Int) = dashboardList[position]

    fun setDashboard(dashboardItems: List<DashboardItem>) {
        val beforeSize = dashboardList.size
        dashboardList.addAll(dashboardItems)
        notifyItemRangeInserted(beforeSize, dashboardItems.size)
    }

    inner class ItemViewHolder(private val binding: ItemDashboardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dashboardItem: DashboardItem) {
            with(binding) {
                viewState = DashboardItemViewState(dashboardItem)
                executePendingBindings()
            }
        }

    }
}