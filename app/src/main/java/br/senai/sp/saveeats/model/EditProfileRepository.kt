package br.senai.sp.saveeats.model

import com.google.gson.JsonObject
import retrofit2.Response

class EditProfileRepository {

    private val service = RetrofitFactory
        .getEditProfile()

    suspend fun editProfile(
        idCliente: Int,
        nome: String,
        email: String,
        senha: String,
        cpf: String,
        foto: String,
        telefone: String,
        cep: String,
        logradouro: String,
        complemento: String,
        bairro: String,
        localidade: String,
        numero: Int,
        uf: String
    ): Response<JsonObject> {

        val requestBody = JsonObject().apply {
            addProperty("id_cliente", idCliente)
            addProperty("nome", nome)
            addProperty("email", email)
            addProperty("senha", senha)
            addProperty("cpf", cpf)
            addProperty("foto", foto)
            addProperty("telefone", telefone)
            addProperty("cep", cep)
            addProperty("logradouro", logradouro)
            addProperty("complemento", complemento)
            addProperty("bairro", bairro)
            addProperty("localidade", localidade)
            addProperty("numero", numero)
            addProperty("uf", uf)
        }

        return service.getEditProfile(requestBody)

    }
}