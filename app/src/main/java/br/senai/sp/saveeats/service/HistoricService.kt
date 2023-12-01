package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.HistoricList
import retrofit2.http.GET
import retrofit2.http.Path

interface HistoricService {

    @GET("/v1/saveeats/detalhes/pedido/idCliente/{id}")
    fun getHistoricByIdClient(@Path("id") id: Int): retrofit2.Call<HistoricList>

}