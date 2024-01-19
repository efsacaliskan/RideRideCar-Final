package com.example.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.model.RideModel


class RideAdapter : ListAdapter<RideModel, RideAdapter.RideViewHolder>(DiffCallback) {

    class RideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSource: TextView = view.findViewById(R.id.tvSource)
        val tvDestination: TextView = view.findViewById(R.id.tvDestination)
    }
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<RideModel>() {
            override fun areItemsTheSame(oldItem: RideModel, newItem: RideModel): Boolean {
                return oldItem.empId == newItem.empId
            }

            override fun areContentsTheSame(oldItem: RideModel, newItem: RideModel): Boolean {
                return oldItem == newItem
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ride, parent, false)
        return RideViewHolder(view)
    }


    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val ride = getItem(position)
        //holder.textViewName.text = ride.empName // Bind data
        holder.tvSource.text = ride.empSource
        holder.tvDestination.text = ride.empDestination
        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putString("name", ride.empName)
                putString("source", ride.empSource)
                putString("destination", ride.empDestination)
                putString("total", ride.empTotalPassenger)
                putString("price", ride.empPrice)
                putString("contact", ride.empContact)
                putString("date", ride.empDate)
                putString("time", ride.empTime)
            }
            holder.itemView.findNavController().navigate(R.id.action_fetchingFragment_to_fragment_ride_details, bundle)
        }

    }



}