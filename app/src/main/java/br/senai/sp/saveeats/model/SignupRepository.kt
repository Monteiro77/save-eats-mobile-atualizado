package br.senai.sp.saveeats.model

import com.google.gson.JsonObject
import retrofit2.Response

class SignupRepository {

    private val service = RetrofitFactory
        .getSignup()

    suspend fun signupClient(name: String, cpf: String, cep: String, state: String, city: String, neighborhood: String, street: String, number: String, email: String, phone: String, password: String, photo: String, complement: String): Response<JsonObject> {

        val requestBody = JsonObject().apply {

            addProperty("nome", name)
            addProperty("email", email)
            addProperty("senha", password)
            addProperty("cpf", cpf)
            addProperty("foto", photo)
            addProperty("telefone", phone)
            addProperty("cep", cep)
            addProperty("logradouro", street)
            addProperty("complemento", complement)
            addProperty("bairro", neighborhood)
            addProperty("localidade", city)
            addProperty("numero", number)
            addProperty("uf", state)

        }

        return service.signupClient(requestBody)

    }

}