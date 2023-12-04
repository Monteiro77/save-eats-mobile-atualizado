package br.senai.sp.saveeats.model

import com.google.gson.JsonObject
import retrofit2.Response

class AvaliationRepository{

    private val service = RetrofitFactory
        .getAvaliation()

    suspend fun avaliation(idClient: Int, idRestaurant: Int, quantidadeEstrela: Int, descricao: String, dataAvaliacao: String, idRecomendacao: Int): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("cliente_id", idClient)
            addProperty("restaurante_id", idRestaurant)
            addProperty("quantidade_estrela", quantidadeEstrela)
            addProperty("descricao", descricao)
            addProperty("data_avaliacao", dataAvaliacao)
            addProperty("recomendacao_id", idRecomendacao)
        }

        return service.avaliation(requestBody)
    }
}
