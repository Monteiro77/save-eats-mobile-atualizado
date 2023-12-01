package br.senai.sp.saveeats.trackorder.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.ui.theme.fontFamily
import coil.compose.AsyncImage
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TrackOrder(navController: NavController, localStorage: Storage) {

    val context = LocalContext.current

    val formatTime = DateTimeFormatter.ofPattern("HH:mm")

    val currentTime = LocalDateTime.now()
    val formattedTime = currentTime.format(formatTime)

    val timeDelivery = localStorage.readDataString(context, "timeDelivery")
    val timeDeliveryFormatted = timeDelivery.toString().replace("00:", "")
    val updatedTime = currentTime.plusMinutes(timeDeliveryFormatted.toLong())
    val formattedTimeUpdated = updatedTime.format(formatTime)

    val imageRestaurant = localStorage.readDataString(context, "imageRestaurant")
    val nameRestaurant = localStorage.readDataString(context, "nameRestaurant")

    val streetClient = localStorage.readDataString(context, "streetClient")
    val numberAddressClient = localStorage.readDataInt(context, "numberAddressClient")
    val cityClient = localStorage.readDataString(context, "cityClient")
    val stateClient = localStorage.readDataString(context, "stateClient")

    var priceProduct = localStorage.readDataString(context, "priceProduct")

    priceProduct = priceProduct!!.replace(",", ".")
    priceProduct.toFloat()

    val deliveryValue = localStorage.readDataFloat(context, "deliveryValue")

    val sumDeliveryProduct = deliveryValue + priceProduct.toFloat()

    val formattedSum = sumDeliveryProduct.let {
        String.format("%.2f", it)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier.offset(x = 30.dp, y = 58.dp)
                ) {

                    IconButton(modifier = Modifier.size(20.dp), onClick = {
                        navController.popBackStack()
                    }) {

                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Arrow",
                            tint = Color(76, 132, 62)
                        )

                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "ACOMPANHAR PEDIDO",
                        fontSize = 18.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W400
                    )

                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier
                        .width(380.dp)
                        .height(240.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = Color(250, 250, 250)
                ) {

                    Row {

                        Column(
                            modifier = Modifier
                                .padding(start = 15.dp, top = 15.dp)
                        ) {

                            Row {

                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = "Icon Check",
                                    tint = colorResource(id = R.color.green_save_eats_light)
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                ) {

                                    Text(
                                        text = "Pedido Confirmado",
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                    Text(
                                        text = "18:40",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                }

                            }

                            Divider(
                                modifier = Modifier
                                    .width(1.5.dp)
                                    .height(20.dp)
                                    .offset(x = 10.dp, y = -(5.dp)),
                                color = Color.Black
                            )

                            Row {

                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = "Icon Check",
                                    tint = Color.Black
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                ) {

                                    Text(
                                        text = "Pedido sendo preparado",
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                    Text(
                                        text = "18:40",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                }

                            }

                            Divider(
                                modifier = Modifier
                                    .width(1.5.dp)
                                    .height(20.dp)
                                    .offset(x = 10.dp, y = -(5.dp)),
                                color = Color.Black
                            )

                            Row {

                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = "Icon Check",
                                    tint = Color.Black
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                ) {

                                    Text(
                                        text = "Pedido a caminho",
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                    Text(
                                        text = "18:40",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                }

                            }

                            Divider(
                                modifier = Modifier
                                    .width(1.5.dp)
                                    .height(20.dp)
                                    .offset(x = 10.dp, y = -(5.dp)),
                                color = Color.Black
                            )

                            Row {

                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = "Icon Check",
                                    tint = Color.Black
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                ) {

                                    Text(
                                        text = "Pedido entregue",
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                    Text(
                                        text = "18:40",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                }

                            }

                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(end = 15.dp, top = 15.dp),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column {

                                Text(
                                    text = "Status do pedido",
                                    fontFamily = fontFamily
                                )

                                Text(
                                    modifier = Modifier
                                        .offset(x = 35.dp),
                                    text = "Despachado",
                                    fontFamily = fontFamily,
                                    color = colorResource(
                                        id =
                                        R.color.green_save_eats_light
                                    )
                                )

                            }

                            Column {

                                Text(
                                    text = "Previsão de entrega",
                                    fontFamily = fontFamily
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(bottom = 15.dp)
                                        .offset(x = 10.dp)
                                ) {

//                                    Icon(
//                                        imageVector = Icons.Default.AccessTime,
//                                        contentDescription = "Icon Time",
//                                        tint = colorResource(id = R.color.green_save_eats_light)
//                                    )

                                    Text(
                                        text = formattedTime.toString(),
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Black
                                    )

//                                    Divider(
//                                        modifier = Modifier
//                                            .width(5.dp)
//                                            .height(1.5.dp)
//                                    )

                                    Text(
                                        text = formattedTimeUpdated.toString(),
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Black
                                    )

                                }

                            }

                        }

                    }

                }

            }
            
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 25.dp)
            ) {

                Text(
                    text = stringResource(id = R.string.order_details),
                    fontSize = 22.sp,
                    fontFamily = fontFamily,
                    color = colorResource(id = R.color.green_save_eats_light)
                )

                Row {

                    AsyncImage(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = CircleShape),
                        model = imageRestaurant,
                        contentDescription = "Image Restaurant"
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {

                        Text(
                            text = nameRestaurant!!
                        )

                        Text(
                            modifier = Modifier
                                .clickable { navController.navigate("products_restaurant_screen") },
                            text = stringResource(id = R.string.see_menu),
                            fontFamily = fontFamily,
                            color = colorResource(id = R.color.green_save_eats_light)
                        )

                    }

                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = stringResource(id = R.string.deliver_to),
                        fontSize = 18.sp,
                        fontFamily = fontFamily
                    )

                    Row {

                        Text(
                            text = "$streetClient,",
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.width(1.dp))

                        Text(
                            text = "$numberAddressClient -",
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                       Text(
                            text = "$cityClient -",
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = stateClient!!,
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                    }

                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.total),
                        fontSize = 18.sp,
                        fontFamily = fontFamily
                    )

                    Text(
                        text = ": R$ $formattedSum",
                        fontWeight = FontWeight.Black,
                        fontFamily = fontFamily
                    )

                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Pagamento:",
                        fontFamily = fontFamily
                    )

                    Text(
                        text = "Pagamento feito pelo aplicativo",
                        fontWeight = FontWeight.Black,
                        fontFamily = fontFamily
                    )

                }

            }

        }

    }

}