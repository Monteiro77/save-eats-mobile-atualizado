package br.senai.sp.saveeats.service

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface EditProfileService {
    @Headers("Content-Type: application/json")
    @PUT("/v1/saveeats/cliente/")
    suspend fun getEditProfile(@Body body: JsonObject): Response<JsonObject>
}