package br.senai.sp.saveeats.presentationcomponents.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.components.CustomButton
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ThirdPresentationScreen(navController: NavController) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.login_animation))

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(350.dp),
                    iterations = LottieConstants.IterateForever
                )

            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.9f)
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Text(
                    modifier = Modifier
                        .width(360.dp),
                    text = "Junte-se a nós como parceiro e ajude a combater o desperdício de alimentos",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(98,98,98)
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){

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

                    Spacer(modifier = Modifier.width(35.dp))

                    Card(
                        Modifier
                            .width(15.dp)
                            .height(15.dp)
                            .clip(shape = CircleShape),
                        backgroundColor = Color(76, 132, 62)
                    ) {}

                }

                CustomButton(
                    onClick = { navController.navigate("login_screen") },
                    text = stringResource(id = R.string.login)
                )

                CustomButton(
                    onClick = { navController.navigate("first_signup_screen") },
                    text = stringResource(id = R.string.signup)
                )

            }


        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun teste() {
    ThirdPresentationScreen(navController = rememberNavController())
}