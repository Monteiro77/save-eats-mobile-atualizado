package br.senai.sp.saveeats.service

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignupService {
    @Headers("Content-Type: application/json")
    @POST("v1/saveeats/cadastro/cliente")
    suspend fun signupClient(@Body body: JsonObject): Response<JsonObject>

}