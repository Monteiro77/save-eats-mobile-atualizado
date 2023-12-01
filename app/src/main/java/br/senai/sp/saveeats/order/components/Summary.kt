package br.senai.sp.saveeats.order.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.ui.theme.fontFamily

@Composable
fun SummaryOfValues(
    textOne: String,
    textTwo: String
) {

    Row (
        modifier = Modifier
            .width(350.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = textOne,
            fontSize = 16.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Light
        )

        Text(
            text = "R$${textTwo.replace(".", ",")}",
            fontSize = 16.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Light
        )

    }

}