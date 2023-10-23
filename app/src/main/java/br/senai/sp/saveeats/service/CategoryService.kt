package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.CategoryList
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {
    @GET("/v1/saveeats/categoria/restaurante")
    fun getCategory(): Call<CategoryList>

}