package br.senai.sp.saveeats.model

data class RecipeDetails(
    val nome_receita: String? = "",
    val foto_receita: String? = "",
    val descricao: String? = "",
    val numero_porcoes: Int? = 0,
    val modo_preparo: String? = "",
    val nivel_dificuldade: String? = "",
    val tempo_preparo: String? = "",
    val ingredientes: List<RecipeIngredients>? = emptyList()
)