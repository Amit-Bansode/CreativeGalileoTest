package com.amit.creativegalileotest.customerpaging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amit.creativegalileotest.R
import com.amit.creativegalileotest.models.CustomersItem

class CustomerPagingAdapter :
    PagingDataAdapter<CustomersItem, CustomerPagingAdapter.CustomerViewHolder>(COMPARATOR) {
    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val customerName = itemView.findViewById<TextView>(R.id.textView)!!
        val tvId = itemView.findViewById<TextView>(R.id.tvId)!!
        val tvMobile = itemView.findViewById<TextView>(R.id.tvMobile)!!
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CustomersItem>() {
            override fun areItemsTheSame(oldItem: CustomersItem, newItem: CustomersItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CustomersItem,
                newItem: CustomersItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val item = getItem(position)!!
        if (item != null) {
            holder.customerName.text = item.name
            holder.tvId.text = item.cgId.toString()
            holder.tvMobile.text = item.mobile
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_customer_item, parent, false)
        return CustomerViewHolder(view)
    }
}