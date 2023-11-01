package br.senai.sp.saveeats.profilerestaurantcomponents.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import coil.compose.AsyncImage

@Composable
fun RestaurantProfile(
    navController: NavController,
    localStorage: Storage
) {

    val context = LocalContext.current


    val nameRestaurant = localStorage.readDataString(context, "nameRestaurant")
    val imageRestaurant = localStorage.readDataString(context, "imageRestaurant")
    val nameCategoryRestaurant = localStorage.readDataString(context, "nameCategoryRestaurant")

    var iconeState by remember{
        mutableStateOf(false)
    }

    var progressState by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {


        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){


            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "",
                tint = Color(33, 150, 38, 255),
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Icon(
                painter =
                if(!iconeState) {
                    painterResource(id = R.drawable.baseline_favorite_border_24)
                }else{
                    painterResource(id = R.drawable.baseline_favorite_24)
                },
                contentDescription  = "",
                tint =
                if(!iconeState) {
                    Color(71, 71, 71, 255)
                }else{
                     Color(255,0,0)
                },
                modifier = Modifier
                    .clickable {
                        iconeState = iconeState != true
                    }
            )
        }

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Column {
                Text(
                    text = nameRestaurant.toString(),
                    color = Color(0,0,0),
                    fontWeight = FontWeight(600),
                    fontSize =  25.sp
                )
                Text(
                    text = nameCategoryRestaurant.toString(),
                    color = Color(0,0,0),
                    fontWeight = FontWeight(300),
                    fontSize =  12.sp
                )
            }

            AsyncImage(
                model  = imageRestaurant,
                contentDescription  = "",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = "4,0",
                color = Color(255,200,0,255),
                fontWeight = FontWeight(400),
                fontSize = 15.sp
            )

            androidx.compose.material.Icon(
                painter = painterResource(id = R.drawable.baseline_star_rate_24),
                contentDescription = "",
                tint = Color(255, 200, 0, 255)
            )
            androidx.compose.material.Icon(
                painter = painterResource(id = R.drawable.baseline_star_rate_24),
                contentDescription = "",
                tint = Color(255, 200, 0, 255)
            )
            androidx.compose.material.Icon(
                painter = painterResource(id = R.drawable.baseline_star_rate_24),
                contentDescription = "",
                tint = Color(255, 200, 0, 255)
            )
            androidx.compose.material.Icon(
                painter = painterResource(id = R.drawable.baseline_star_rate_24),
                contentDescription = "",
                tint = Color(255, 200, 0, 255)
            )
            androidx.compose.material.Icon(
                painter = painterResource(id = R.drawable.baseline_star_border_24),
                contentDescription = "",
                modifier = Modifier.size(15.dp)
            )
        }

        Text(
            text = "355 Avaliações",
            fontWeight = FontWeight(300),
            fontSize = 12.sp
        )

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Avaliações",
                    fontSize = 17.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 55.dp)
                        .clickable { progressState = true }

                )



                Text(
                    text = "Informações",
                    fontSize = 17.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(end = 50.dp)
                        .clickable { progressState = false }
                )


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(
                        color =
                        if (progressState) {
                            Color(218, 218, 218)
                        } else {
                            Color(41, 97, 27)
                        },
                        shape = RoundedCornerShape(size = 5.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .width(180.dp)
                        .height(2.dp)
                        .background(
                            color =
                            if (!progressState) {
                                Color(218, 218, 218)
                            } else {
                                Color(41, 97, 27)
                            },
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                ) {}
            }

        }

        if (progressState) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Avalaição")
            }
        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Informações Restaurante")
            }

        }







    }


}

