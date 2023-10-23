package br.senai.sp.saveeats.service

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Content-Type: application/json")
    @POST("v1/saveeats/cliente/login/autenticar")
    suspend fun authenticationClient(@Body body: JsonObject): Response<JsonObject>

}