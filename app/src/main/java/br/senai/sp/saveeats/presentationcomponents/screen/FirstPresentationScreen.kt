package br.senai.sp.saveeats.presentationcomponents.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.components.CustomButton
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun FirstPresentationScreen(navController: NavController) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.chef))

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(modifier = Modifier) {

                    Surface (
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                            .offset(x = 10.dp, y = 120.dp),
                        color = Color(76,132,62),
                        shape = CircleShape
                    ) {}

                    Surface (
                        modifier = Modifier
                            .width(230.dp)
                            .height(230.dp)
                            .offset(x = 130.dp, y = 90.dp),
                        color = Color(76,132,62),
                        shape = CircleShape
                    ) {}

                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier.size(350.dp),
                        iterations = LottieConstants.IterateForever
                    )

                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Text(
                    modifier = Modifier
                        .width(370.dp),
                    text = "Participe deste movimento sustentável que combate o desperdício de alimentos ao resgatar produtos frescos de restaurantes, padarias e hortifrutis com descontos de até 70%",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(98, 98, 98)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Card(
                        Modifier
                            .width(15.dp)
                            .height(15.dp)
                            .clip(shape = CircleShape),
                        backgroundColor = Color(76, 132, 62)
                    ) {}

                    Spacer(modifier = Modifier.width(35.dp))

                    Card(
                        Modifier
                            .width(15.dp)
                            .height(15.dp)
                            .clip(shape = CircleShape),
                        backgroundColor = Color(217, 217, 217)
                    ) {}

                    Spacer(modifier = Modifier.width(35.dp))

                    Card(
                        Modifier
                            .width(15.dp)
                            .height(15.dp)
                            .clip(shape = CircleShape),
                        backgroundColor = Color(217, 217, 217)
                    ) {}

                }

                CustomButton(
                    onClick = { navController.navigate("second_presentation_screen") },
                    text = stringResource(id = R.string.next)
                )

            }


        }

    }
}