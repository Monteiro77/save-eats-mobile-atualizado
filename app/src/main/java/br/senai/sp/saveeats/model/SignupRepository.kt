package br.senai.sp.saveeats.model

import com.google.gson.JsonObject
import retrofit2.Response

class SignupRepository {

    private val service = RetrofitFactory
        .getSignup()

    suspend fun signupClient(name: String, cpf: String, cep: String, uf: String, city: String, neighborhood: String, street: String, number: String, email: String, phone: String, password: String): Response<JsonObject> {

        val requestBody = JsonObject().apply {

            addProperty("nome", name)
            addProperty("cpf", cpf)
            addProperty("cep", cep)
            addProperty("uf", uf)
            addProperty("localidade", city)
            addProperty("bairro", neighborhood)
            addProperty("logradouro", street)
            addProperty("numero", number)
            addProperty("email", email)
            addProperty("telefone", phone)
            addProperty("senha", password)

        }

        return service.signupClient(requestBody)

    }

}