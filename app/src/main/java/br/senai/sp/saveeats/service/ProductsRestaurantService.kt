package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.ProductsRestaurantList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsRestaurantService {
    @GET("v1/saveeats/restaurante/produtos/nome-fantasia/{name_restaurant}")
    fun getProductsRestaurant(@Path("name_restaurant") name_restaurant: String): Call<ProductsRestaurantList>

}