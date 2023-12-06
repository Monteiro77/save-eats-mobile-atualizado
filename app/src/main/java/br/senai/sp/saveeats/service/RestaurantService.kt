package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.AddressRestaurantList
import br.senai.sp.saveeats.model.AvaliationList
import br.senai.sp.saveeats.model.HorarioDeFuncionamentoList
import br.senai.sp.saveeats.model.RestaurantList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantService {
    @GET("/v1/saveeats/restaurantes")
    fun getRestaurantCall(): Call<RestaurantList>

    @GET("/v1/saveeats/restaurantes")
    suspend fun getRestaurantResponse(): Response<RestaurantList>

    @GET("/v1/saveeats/avaliacoes/restaurante/idRestaurante/{id}")
    fun getRestaurantRate(@Path("id") id: Int): Call<AvaliationList>

    @GET("/v1/saveeats/endereco/restaurante/id/{id}")
    fun getAdressRestaurantByID(@Path("id") id: Int): Call<AddressRestaurantList>

    @GET("/v1/saveeats/restaurante/dia-horario-funcionamento/idRestaurante/{id}")
    fun getHorarioDeFuncionamentoByIdRestaurante(@Path("id") id: Int): Call<HorarioDeFuncionamentoList>



}