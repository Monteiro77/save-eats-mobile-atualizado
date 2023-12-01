package br.senai.sp.saveeats.tips.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import coil.compose.AsyncImage

@Composable
fun TipScreen(localStorage: Storage) {

    val context = LocalContext.current

    val imageTip = localStorage.readDataString(context,"imageTip")
    val nameTip = localStorage.readDataString(context,"nameTip")
    val descriptionTip = localStorage.readDataString(context,"descriptionTip")
    val categoryTip = localStorage.readDataString(context,"categoryTip")

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box {

                Icon(
                    modifier = Modifier
                        .offset(x = -(170.dp), y = 50.dp),
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Arrow Back",
                    tint = Color(20, 58, 11)
                )

            }

            Text(
                modifier = Modifier
                    .width(250.dp)
                    .offset(y = 20.dp),
                text = nameTip!!,
                fontSize = 22.sp,
                fontWeight = FontWeight.W500,
                color = Color(20, 58, 11),
                textAlign = TextAlign.Center
            )

            Divider(
                modifier = Modifier
                    .width(380.dp)
                    .height(2.dp)
                    .offset(y = 30.dp),
                color = Color(200,200,200)
            )

        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface (
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
            ) {

                AsyncImage(
                    model = imageTip!!,
                    contentDescription = "Image Tips"
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = descriptionTip!!
            )

        }

    }

}