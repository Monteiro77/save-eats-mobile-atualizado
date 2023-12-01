package br.senai.sp.saveeats.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.ui.theme.fontFamily

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String
) {

    Button(
        onClick,
        modifier = Modifier
            .width(215.dp)
            .height(55.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(76,132,62)
        )
       ) {

        Text(
            text = text.uppercase(),
            fontSize = 20.sp,
            fontFamily = fontFamily,
            color = Color(255,255,255)
        )
        
    }
    
}