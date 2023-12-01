package br.senai.sp.saveeats.order.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.ui.theme.fontFamily

@Composable
fun Title(
    text: String
) {

    Text(
        text = text,
        fontSize = 18.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Black,
        color = colorResource(id = R.color.green_save_eats_light)
    )

}