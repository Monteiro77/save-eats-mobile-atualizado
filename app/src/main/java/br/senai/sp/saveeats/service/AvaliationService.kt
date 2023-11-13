package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.FormPaymentList
import br.senai.sp.saveeats.model.RecomendationsList
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface AvaliationService {

    @Headers("Content-Type: application/json")
    @POST("/v1/saveeats/cliente-avaliar-restaurante")
    suspend fun postAvaliation(@Body body: JsonObject): Response<JsonObject>

    @GET("/v1/saveeats/recomendacao")
    fun getRecomendacoes(): Call<RecomendationsList>


}