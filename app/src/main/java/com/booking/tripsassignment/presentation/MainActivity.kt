package com.booking.tripsassignment.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.booking.tripsassignment.R
import com.booking.tripsassignment.domain.usecase.GetChainsUseCase
import com.booking.tripsassignment.repository.MockNetworkBookingRepository

class MainActivity : AppCompatActivity() {

    private lateinit var upcomingChainRecyclerView: RecyclerView
    private lateinit var pastChainRecyclerView: RecyclerView
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        mainViewModel = MainViewModel(GetChainsUseCase(MockNetworkBookingRepository()))
        upcomingChainRecyclerView=findViewById(R.id.upcomingChainRecyclerView)
        pastChainRecyclerView=findViewById(R.id.pastChainRecyclerView)

        upcomingChainRecyclerView.layoutManager=LinearLayoutManager(this)
        pastChainRecyclerView.layoutManager=LinearLayoutManager(this)

        mainViewModel.getBooking(899848)

        mainViewModel.upcomingChains.observe(this) {
            upcomingChainRecyclerView.adapter = mainViewModel.upcomingChains.value?.let { it -> ChainAdapter(it) }
        }

        mainViewModel.pastChains.observe(this) {
            pastChainRecyclerView.adapter = mainViewModel.pastChains.value?.let { it -> ChainAdapter(it) }
        }

    }
}