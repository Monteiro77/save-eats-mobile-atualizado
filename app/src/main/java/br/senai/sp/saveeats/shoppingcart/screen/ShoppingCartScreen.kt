package br.senai.sp.saveeats.shoppingcart.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import br.senai.sp.saveeats.components.CustomButton
import br.senai.sp.saveeats.components.Header
import br.senai.sp.saveeats.model.DeliveryAreaRestaurant
import br.senai.sp.saveeats.model.DeliveryAreaRestaurantList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.presentation.screen.teste
import br.senai.sp.saveeats.ui.theme.fontFamily
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ShoppingCartScreen(navController: NavController, localStorage: Storage) {

    val context = LocalContext.current

    val imageProduct = localStorage.readDataString(context, "imageProduct")
    val nameProduct = localStorage.readDataString(context, "nameProduct")
    var priceProduct = localStorage.readDataString(context, "priceProduct")

    priceProduct = priceProduct!!.replace(",", ".")

    priceProduct.toFloat()

    var listDeliveryArea by remember {
        mutableStateOf(DeliveryAreaRestaurant())
    }

    val callDeliveryArea = RetrofitFactory
        .getDeliveryArea()
        .getDeliveryArea(5)

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

    Log.e("TESTE", "ShoppingCartScreen: ${listDeliveryArea.valor_entrega}", )

    val sumDeliveryProduct = listDeliveryArea.valor_entrega!! + priceProduct.toFloat()

    val formattedSum = sumDeliveryProduct?.let {
        String.format("%.2f", it)
    }

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

            Header(
                text = stringResource(id = R.string.shopping_cart),
                navController = navController
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier
                        .width(340.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column {

                            AsyncImage(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(shape = CircleShape),
                                model = imageProduct,
                                contentDescription = "Image Product",
                                contentScale = ContentScale.FillHeight
                            )

                        }

                        Column {

                            Text(
                                text = nameProduct!!,
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Black
                            )

                            Text(
                                text = "Unit: R$$priceProduct",
                                fontSize = 15.sp,
                                fontFamily = fontFamily
                            )

                        }

                        Column {

                            IconButton(onClick = { /*TODO*/ }) {

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Icon Delete",
                                    tint = colorResource(id = R.color.green_save_eats_light)
                                )

                            }

                        }

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
                    color = colorResource(id = R.color.white),
                    elevation = 5.dp,
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
                                .offset(x = -(90).dp),
                            text = stringResource(id = R.string.summary_of_values),
                            fontSize = 22.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black,
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
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.W300
                            )

                            Text(
                                text = "R$$priceProduct".replace(".", ","),
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Black
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
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.W300
                            )

                            Text(
                                text = "R$${listDeliveryArea.valor_entrega}".replace(".", ","),
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Black
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
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.W300
                            )

                            Text(
                                text = "R$$formattedSum".replace(".", ","),
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Black
                            )

                        }

                        CustomButton(
                            onClick = {
                                localStorage.saveDataString(
                                    context,
                                    priceProduct,
                                    "priceProduct"
                                )
                                localStorage.saveDataFloat(
                                    context,
                                    listDeliveryArea.valor_entrega!!,
                                    "deliveryValue"
                                )
                                localStorage.saveDataString(
                                    context,
                                    listDeliveryArea.tempo_entrega!!,
                                    "deliveryTime"
                                )
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