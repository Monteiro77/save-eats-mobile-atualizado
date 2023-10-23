package br.senai.sp.saveeats.model

import com.google.gson.JsonObject
import retrofit2.Response

class LoginRepository {

    private val service = RetrofitFactory
        .getLogin()

    suspend fun loginClient(email: String, password: String) : Response<JsonObject> {
        val requestBody = JsonObject().apply {

            addProperty("email", email)
            addProperty("senha", password)

        }

        return service.authenticationClient(requestBody)

    }

}