package br.senai.sp.saveeats.model

data class Tips(
    val id_categoria: Int,
    val categoria: String,
    val nome_da_receita: String,
    val foto_da_receita: String,
    val descricao_da_receita: String
)
