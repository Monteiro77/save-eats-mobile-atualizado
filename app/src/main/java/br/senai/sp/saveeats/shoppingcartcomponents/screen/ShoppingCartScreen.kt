package br.senai.sp.saveeats.shoppingcartcomponents.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.CustomButton
import br.senai.sp.saveeats.model.DeliveryAreaRestaurant
import br.senai.sp.saveeats.model.DeliveryAreaRestaurantList
import br.senai.sp.saveeats.model.ProductsRestaurant
import br.senai.sp.saveeats.model.ProductsRestaurantList
import br.senai.sp.saveeats.model.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ShoppingCartScreen(navController: NavController, localStorage: Storage) {

    val context = LocalContext.current

    val idRestaurant = localStorage.readDataInt(context, "idRestaurant")

    val imageProduct = localStorage.readDataString(context, "imageProduct")
    val nameProduct = localStorage.readDataString(context, "nameProduct")
    val priceProduct = localStorage.readDataFloat(context, "priceProduct")

    var listDeliveryArea by remember {
        mutableStateOf(DeliveryAreaRestaurant())
    }

    val callDeliveryArea = RetrofitFactory
        .getDeliveryArea()
        .getDeliveryArea(idRestaurant)

    callDeliveryArea.enqueue(object : Callback<DeliveryAreaRestaurantList> {
        override fun onResponse(
            call: Call<DeliveryAreaRestaurantList>,
            response: Response<DeliveryAreaRestaurantList>
        ) {
            listDeliveryArea = response.body()!!.frete_area_entrega_do_restaurante
        }

        override fun onFailure(
            call: Call<DeliveryAreaRestaurantList>,
            t: Throwable
        ) {

            Log.e("ERROR", "onFailure: ${t.message}")

        }

    })

    val sumDeliveryProduct = listDeliveryArea.valor_entrega?.plus(priceProduct)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .offset(x = -(90).dp)
                ) {

                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Arrow Back",
                            tint = colorResource(id = R.color.green_save_eats_dark)
                        )

                    }

                }

                Text(
                    text = stringResource(id = R.string.shopping_cart),
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.green_save_eats_dark)
                )

            }

            Divider(
                modifier = Modifier
                    .width(320.dp)
                    .height(2.dp),
                colorResource(id = R.color.green_save_eats_dark)
            )

            Spacer(
                modifier = Modifier
                    .height(35.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(470.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier
                        .width(320.dp)
                        .height(90.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 3.dp
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Spacer(modifier = Modifier.width(8.dp))

                        AsyncImage(
                            modifier = Modifier
                                .width(68.dp)
                                .height(65.dp)
                                .clip(shape = CircleShape),
                            model = imageProduct,
                            contentDescription = "Image Product",
                            contentScale = ContentScale.FillHeight
                        )
                        Spacer(modifier = Modifier.width(20.dp))

                        Column {

                            Text(
                                text = nameProduct!!,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400
                            )

                            Text(
                                text = "Unit: R$$priceProduct",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W300
                            )

                        }

                        Spacer(modifier = Modifier.width(50.dp))

                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = colorResource(id = R.color.green_save_eats_dark)
                        )

                    }

                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(240, 240, 240),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {

                        Text(
                            modifier = Modifier
                                .offset(-(95).dp),
                            text = stringResource(id = R.string.summary_of_values),
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.green_save_eats_light)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 25.dp, end = 25.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = stringResource(id = R.string.subtotal),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W300
                            )

                            Text(
                                text = "R$$priceProduct",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W500
                            )

                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 25.dp, end = 25.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = stringResource(id = R.string.delivery_fee),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W300
                            )

                            Text(
                                text = listDeliveryArea.valor_entrega.toString(),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W500
                            )

                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 25.dp, end = 25.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = stringResource(id = R.string.total),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W300
                            )

                            Text(
                                text = "R$$sumDeliveryProduct",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W500
                            )

                        }

                        CustomButton(
                            onClick = {
                                localStorage.saveDataFloat(context, priceProduct, "priceProduct")
                                localStorage.saveDataFloat(context, listDeliveryArea.valor_entrega!!, "deliveryValue")
                                localStorage.saveDataString(context, listDeliveryArea.tempo_entrega!!, "deliveryTime")
                                navController.navigate("order_screen")
                            },
                            text = stringResource(id = R.string.next)
                        )

                    }

                }

            }

        }

    }

}