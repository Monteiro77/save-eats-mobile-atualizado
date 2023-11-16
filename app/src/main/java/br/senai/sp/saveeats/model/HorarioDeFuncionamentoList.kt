package br.senai.sp.saveeats.model

data class HorarioDeFuncionamentoList(
    val status: Int,
    val message: String,
    val dias_horarios_funcionamento: List<HorarioDeFuncionamento>
)
