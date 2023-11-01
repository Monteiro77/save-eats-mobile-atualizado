package br.senai.sp.saveeats.model

data class FormPaymentList(
    val status: Int,
    val message: String,
    val formas_de_pagamento_do_restaurante: List<FormPayment>
)
