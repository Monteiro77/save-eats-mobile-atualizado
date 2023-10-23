package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.CategoryRestaurantList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryRestaurantService {
    @GET("v1/saveeats/restaurante/{name_category}")
    fun getCategoryRestaurant(@Path("name_category") name_category: String): Call<CategoryRestaurantList>

}