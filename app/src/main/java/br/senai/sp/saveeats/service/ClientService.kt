package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.ClientAddress
import br.senai.sp.saveeats.model.ClientAddressList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientService {
    @GET("/v1/saveeats/endereco/cliente/idcliente/{id}")
    fun getAddressClient(@Path("id") id: Int):Call<ClientAddressList>

}