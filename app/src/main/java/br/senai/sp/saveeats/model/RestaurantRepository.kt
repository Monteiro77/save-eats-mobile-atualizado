package br.senai.sp.saveeats.model

import br.senai.sp.saveeats.service.RestaurantService
import retrofit2.Call
import retrofit2.Response

class RestaurantRepository {

    private val apiService = RetrofitFactory.getRestaurant()
    suspend fun getRestaurant(): Response<RestaurantList> {
        return apiService.getRestaurantResponse()
    }

}