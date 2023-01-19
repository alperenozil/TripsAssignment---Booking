package com.booking.tripsassignment.domain.usecase

import com.booking.tripsassignment.data.Booking
import com.booking.tripsassignment.data.Chain
import com.booking.tripsassignment.repository.MockNetworkBookingRepository

class GetChainsUseCase(
    private val repo: MockNetworkBookingRepository
) {
    operator fun invoke(tripDate: TripDate, userId: Int): List<Chain> {

        val tempList: MutableList<Booking> = mutableListOf() // for detecting chains
        val chainList: MutableList<Chain> = mutableListOf() // add previously created chains to list
        val list=repo.fetchBookings(userId).data()?.sortedBy { it.checkin.toString() }

        list?.let {
            for(i in 0..list.size-2){
                if(list.get(i).checkout==list.get(i+1).checkin){
                    if(!tempList.contains(list.get(i))) tempList.add(list.get(i)) // prevent duplicates
                    tempList.add(list.get(i+1))
                    if(i==list.size-2){ // if we are at the end of the list and we detected a chain
                        chainList.add(Chain(trips = tempList.toList() as MutableList<Booking>))
                    }
                } else { // when chain is broken add it to list
                    if(tempList.size>1){
                        chainList.add(Chain(trips = tempList.toList() as MutableList<Booking>))
                    }
                    tempList.clear()
                }
            }
        }

        chainList.forEach {
            println("##### chain list")
            it.trips.forEach { print(it.hotel.cityName+" ") }
        }

        return when(tripDate){
            TripDate.PAST -> chainList.filter {
                it.trips[chainList.size].checkout.year<2023
            }
            TripDate.UPCOMING -> chainList.filter {
                it.trips[chainList.size].checkout.year>=2023
            }
        }

    }
}

enum class TripDate {
    UPCOMING, PAST
}