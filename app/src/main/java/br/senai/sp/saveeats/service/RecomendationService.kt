package br.senai.sp.saveeats.service


import br.senai.sp.saveeats.model.RecomendationsList
import retrofit2.Call
import retrofit2.http.GET

interface RecomendationService {

    @GET("/v1/saveeats/recomendacao")
    fun getRecomendation(): Call<RecomendationsList>
}