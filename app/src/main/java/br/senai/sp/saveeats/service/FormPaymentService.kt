package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.FormPaymentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface FormPaymentService {
    @GET("v1/saveeats/restaurante/forma-pagamento/idRestaurante/{id_restaurant}")
    fun getFormPayment(@Path("id_restaurant") id_restaurant: Int): Call<FormPaymentList>

}