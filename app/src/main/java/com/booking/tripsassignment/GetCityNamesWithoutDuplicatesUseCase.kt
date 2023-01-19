package com.booking.tripsassignment

import com.booking.tripsassignment.data.Booking

class GetCityNamesWithoutDuplicatesUseCase {
    operator fun invoke(cities: MutableList<Booking>): String {
        val result = StringBuilder()
        val tempList: MutableList<String> = mutableListOf()
        val newList: MutableList<String> = mutableListOf()
        cities.forEach {
            tempList.add(it.hotel.cityName)
        }
        for(index in 0..tempList.size-2){
            if(tempList.get(index)!=tempList.get(index+1)){
                newList.add(tempList.get(index))
            }
        }
        for (city in newList){
            result.append(city)
            result.append(" ")
        }
        return result.toString()
    }
}