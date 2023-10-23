package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.CategoryTipsList
import br.senai.sp.saveeats.model.TipsList
import retrofit2.Call
import retrofit2.http.GET

interface TipsService {
    @GET("/v1/saveeats/dicas")
    fun getTips(): Call<TipsList>

    @GET("/v1/saveeats/categoria/dicas")
    fun getCategoryTips(): Call<CategoryTipsList>

}