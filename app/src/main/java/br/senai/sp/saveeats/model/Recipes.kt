package br.senai.sp.saveeats.model

data class Recipes(
    val id_receita: Int,
    val nome_receita: String,
    val foto_receita: String,
    val descricao: String,
    val numero_porcoes: Int,
    val modo_preparo: String,
    val nivel_dificuldade: String,
    val tempo_preparo: String
)
