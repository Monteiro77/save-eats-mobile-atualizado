package br.senai.sp.saveeats.profilecomponents.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.saveeats.R

@Composable
fun ProfileScreen() {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box (
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                IconButton(onClick = { /*TODO*/ }) {

                    Icon(
                        modifier = Modifier
                            .offset(x = 10.dp, y = 10.dp),
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Arrow Back",
                        tint = colorResource(id = R.color.green_save_eats_light)
                    )

                }

            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.foto),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )

            }

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun teste() {
    ProfileScreen()
}