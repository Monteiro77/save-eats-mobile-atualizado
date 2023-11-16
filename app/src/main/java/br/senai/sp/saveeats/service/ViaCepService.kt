package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.AdressClientViaCep
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepService {

//    @GET("ws/{cep}/json/")
//    fun getAdressClientByViaCep(@Path("cep")cep: Int ): Call<AdressClientViaCep>

        @GET("ws/{cep}/json/")
     suspend fun getAdressClientByViaCep(@Path("cep") cep: String ): Response<AdressClientViaCep>


}