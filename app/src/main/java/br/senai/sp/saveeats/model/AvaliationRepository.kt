package br.senai.sp.saveeats.model

import com.google.gson.JsonObject
import retrofit2.Response

class AvaliationRepository {

    val service = RetrofitFactory
        .postAvaliation()

    suspend fun createAvaliation(
        cliente_id: Int,
        restaurante_id: Int,
        quantidade_estrela: Int,
        descricao: String,
        data_avaliacao: String,
        recomendacao_id: Int
    ): Response<JsonObject> {

        val requestBody = JsonObject().apply {
            addProperty("cliente_id", cliente_id)
            addProperty("restaurante_id", restaurante_id)
            addProperty("quantidade_estrela", quantidade_estrela)
            addProperty("descricao", descricao)
            addProperty("data_avaliacao", data_avaliacao)
            addProperty("recomendacao_id", recomendacao_id)
        }

        return service.postAvaliation(requestBody)

    }
}