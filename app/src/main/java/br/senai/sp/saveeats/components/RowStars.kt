package br.senai.sp.saveeats.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.senai.sp.saveeats.R

@Composable
fun RowStars() {

    Row (
        modifier = Modifier
            .width(120.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription ="",
            modifier = Modifier.size(20.dp),
            tint = Color(255, 229, 0, 255)
        )
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription ="",
            modifier = Modifier.size(20.dp),
            tint = Color(255, 229, 0, 255)
        )
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription ="",
            modifier = Modifier.size(20.dp),
            tint = Color(255, 229, 0, 255)
        )
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription ="",
            modifier = Modifier.size(20.dp),
            tint = Color(255, 229, 0, 255)
        )
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription ="",
            modifier = Modifier.size(20.dp),
            tint = Color(255, 229, 0, 255)
        )

    }

}