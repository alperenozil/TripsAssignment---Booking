package com.booking.tripsassignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val getChainsUseCase: GetChainsUseCase
    ) : ViewModel() {

    val upcomingChains: MutableLiveData<List<Chain>> = MutableLiveData()
    val pastChains: MutableLiveData<List<Chain>> = MutableLiveData()

    fun getBooking(userId: Int) {
        GlobalScope.launch {
            upcomingChains.postValue(getChainsUseCase.invoke(TripDate.UPCOMING,userId))
            pastChains.postValue(getChainsUseCase.invoke(TripDate.PAST,userId))
        }
    }

}