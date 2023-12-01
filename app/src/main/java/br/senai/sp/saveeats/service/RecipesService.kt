package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.CategoryRecipes
import br.senai.sp.saveeats.model.CategoryRecipesList
import br.senai.sp.saveeats.model.ClientAddressList
import br.senai.sp.saveeats.model.RecipeDetailsList
import br.senai.sp.saveeats.model.RecipesList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipesService {

    @GET("/v1/saveeats/receitas")
    fun getRecipes(): Call<RecipesList>

    @GET("/v1/saveeats/categoria/receitas")
    fun getCategoryRecipes(): Call<CategoryRecipesList>

    @GET("/v1/saveeats/detalhes/receitas/id/{id}")
    fun getRecipeDetails(@Path("id") id: Int):Call<RecipeDetailsList>

}