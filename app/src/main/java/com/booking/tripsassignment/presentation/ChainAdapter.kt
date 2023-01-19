package com.booking.tripsassignment.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.booking.tripsassignment.R
import com.booking.tripsassignment.data.Chain
import com.booking.tripsassignment.domain.usecase.GetCityNamesWithoutDuplicatesUseCase
import com.booking.tripsassignment.utils.ImageLoader

class ChainAdapter(private val chainList: List<Chain>): RecyclerView.Adapter<ChainAdapter.ChainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChainViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.trip_card_item_layout, parent, false)
        return ChainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChainViewHolder, position: Int) {
        val currentItem=chainList.get(position)
        ImageLoader.loadImage(holder.imageViewTrip,currentItem.trips.get(0).hotel.mainPhoto) // TODO fix image loading problem
        holder.textViewCities.text="Trip to "+ GetCityNamesWithoutDuplicatesUseCase().invoke(currentItem.trips)
        holder.textViewDates.text=currentItem.trips.get(0).checkin.toString()+" - "+chainList.get(position).trips.get(chainList.size-1).checkout
        holder.textViewNights.text=currentItem.trips.size.toString()+" bookings"
    }

    override fun getItemCount(): Int {
        return chainList.size
    }

    class ChainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCities: TextView = itemView.findViewById(R.id.cities)
        val textViewDates: TextView = itemView.findViewById(R.id.dates)
        val textViewNights: TextView = itemView.findViewById(R.id.nights)
        val imageViewTrip: ImageView = itemView.findViewById(R.id.trip_image)
    }
}