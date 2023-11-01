package br.senai.sp.saveeats.productcomponents.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController,
    localStorage: Storage,
) {

    val context = LocalContext.current

    val idProduct = localStorage.readDataInt(context, "idProduct")
    val imageProduct = localStorage.readDataString(context, "imageProduct")
    val nameProduct = localStorage.readDataString(context, "nameProduct")
    val priceProduct = localStorage.readDataFloat(context, "priceProduct")
    val descriptionProduct = localStorage.readDataString(context, "descriptionProduct")

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                shape = RoundedCornerShape(bottomStart = 35.dp, bottomEnd = 35.dp)
            ) {

                AsyncImage(
                    model = imageProduct,
                    contentDescription = "Image Product",
                    contentScale = ContentScale.FillBounds
                )

            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 20.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    Text(
                        text = nameProduct!!,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W400,
                        letterSpacing = 2.sp
                    )

                    Text(
                        text = descriptionProduct!!,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W300
                    )

                    Text(
                        text = "from R$ $priceProduct",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        letterSpacing = 2.sp,
                        color = Color(20, 58, 11)
                    )

                }

                Spacer(modifier = Modifier.height(40.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    Text(
                        text = stringResource(id = R.string.any_observations),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .width(370.dp)
                            .height(150.dp),
                        value = "",
                        onValueChange = {}
                    )


                }

            }

            Spacer(modifier = Modifier.height(40.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                androidx.compose.material.Button(
                    onClick = {
                        localStorage.readDataString(context, "imageProduct")
                        localStorage.readDataString(context, "nameProduct")
                        localStorage.readDataFloat(context, "priceProduct")
                        localStorage.readDataString(context, "descriptionProduct")
                        navController.navigate("shopping_cart_screen")
                    },
                    modifier = Modifier
                        .width(255.dp)
                        .height(55.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(76, 132, 62)
                    )

                )

                {

                    Text(
                        text = stringResource(id = R.string.add_to_cart).uppercase(),
                        fontSize = 16.sp,
                        color = Color.White
                    )

                }

            }

        }

    }

}