package br.senai.sp.saveeats.historic.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.OrderInformation
import br.senai.sp.saveeats.model.OrderList
import br.senai.sp.saveeats.model.ProductOrderList
import br.senai.sp.saveeats.model.RetrofitFactory
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import retrofit2.Call
import retrofit2.Response
import java.util.Locale

@Composable
fun DetalhesPedidoHistoricoScreen(
    navController: NavController,
    localStorage: Storage
) {


    val waitingAnimation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.waiting_animation))

    val verifierAnimation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.verified_animation))


    val context = LocalContext.current


    var productsOrder by remember {
        mutableStateOf(
            listOf<ProductOrderList>()
        )
    }

    var detalhesPedido by remember {
        mutableStateOf(
            listOf<OrderInformation>()
        )
    }


    val idOrder = localStorage.readDataInt(context, "idOrder")



    Log.e("TESTE100", "DetalhesPedidoHistoricoScreen: $idOrder")
    val callPedido = RetrofitFactory
        .getOrderById()
        .getOrderById(idOrder)


    var status by remember {
        mutableStateOf(false)
    }
    callPedido.enqueue(object : retrofit2.Callback<OrderList> {
        override fun onResponse(
            call: Call<OrderList>,
            response: Response<OrderList>
        ) {
            detalhesPedido = listOf(response.body()!!.detalhes_do_pedido)
            productsOrder = response.body()!!.detalhes_do_pedido.produtos
            status = true
        }

        override fun onFailure(
            call: Call<OrderList>,
            t: Throwable
        ) {
            Log.e("TESTE2", "onFailure: ${t.message}")
        }

    })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        navController.popBackStack()
                    }
                    .absoluteOffset(x = (-30).dp),
                tint = Color(20, 58, 11)

            )

            Spacer(modifier = Modifier.width(50.dp))

            Text(
                text = stringResource(id = R.string.details_orders),
                fontSize = 25.sp,
                color = Color(20, 58, 11),
                modifier = Modifier.absoluteOffset(x = (-80).dp)
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        if (status) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                AsyncImage(
                    model = localStorage.readDataString(context, "foto_restaurante"),
                    contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(20.dp))

                Column(
                    modifier = Modifier.absoluteOffset(x = (-15).dp)
                ) {
                    Text(
                        text = detalhesPedido[0].nome_restaurante,
                        fontSize = 20.sp,
                        color = Color(0, 0, 0),
                        fontWeight = FontWeight(500)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = detalhesPedido[0].numero_pedido,
                        color = Color(104, 104, 104),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Ver Cardápio",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(41, 95, 27),
                        modifier = Modifier
                            .clickable {
                                navController.navigate("products_restaurant_screen")
                                localStorage.saveDataString(
                                    context,
                                    detalhesPedido[0].nome_restaurante,
                                    "nameRestaurant"
                                )

                            }

                    )
                }

            }

            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                colors = CardDefaults.cardColors(
                    Color(239, 239, 239)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        if (detalhesPedido[0].status_pedido == "Pedido entregue") {
                            LottieAnimation(
                                composition = verifierAnimation,
                                modifier = Modifier.size(20.dp),
                                iterations = LottieConstants.IterateForever
                            )


                        } else {
                            LottieAnimation(
                                composition = waitingAnimation,
                                modifier = Modifier.size(20.dp),
                                iterations = LottieConstants.IterateForever
                            )

                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = detalhesPedido[0].status_pedido,
                            fontSize = 17.sp
                        )

                    }
                }
            }


            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.5.dp),
                colors = CardDefaults.cardColors(
                    Color(255, 141, 6)
                )
            ) {}

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn {
                items(productsOrder) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(
                            model = it.imagem_produto,
                            contentDescription = "Imagem do produto",
                            modifier = Modifier
                                .size(65.dp)
                        )


                        Column(
                            modifier = Modifier
                                .width(250.dp),
                            horizontalAlignment = Alignment.Start
                        ) {

                            Text(
                                text = it.nome_produto,
                                fontSize = 18.sp
                            )

                            Row {

                                Text(
                                    text = it.descricao_produto,
                                    color = Color(104, 104, 104),
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Text(text = it.preco_produto)


                    }

                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Column {

                Text(
                    text = "Resumo de valores",
                    fontSize = 20.sp,
                    color = Color(41, 95, 27)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "SubTotal",
                        color = Color(104, 104, 104),
                        fontSize = 12.sp
                    )



                    Text(
                        text = "R$ ${detalhesPedido[0].valor_total}"
                    )

                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Taxa de entrega",
                        color = Color(104, 104, 104),
                        fontSize = 12.sp
                    )


                    Text(
                        text = "R$ ${detalhesPedido[0].valor_entrega}",
                        color = Color(41, 95, 27)
                    )

                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Total",
                        color = Color(104, 104, 104),
                        fontSize = 12.sp
                    )

                    Text(
                        text = "R$ ${
                            calculoTotal(
                                detalhesPedido[0].valor_total.replace(",", ".").toFloat(),
                                detalhesPedido[0].valor_entrega.replace(",", ".").toFloat()
                            )
                        }"
                    )


                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Pagamento",
                    fontSize = 20.sp,
                    color = Color(41, 95, 27)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = detalhesPedido[0].nome_forma_pagamento,
                        color = Color(104, 104, 104),
                        fontSize = 12.sp
                    )



                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_pix_24),
                            contentDescription = "",
                            tint = Color(22, 77, 20, 255),
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Pix",
                        )
                    }

                }


                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Endereço de entrega",
                    fontSize = 20.sp,
                    color = Color(41, 95, 27)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = localStorage.readDataString(context, "cep_cliente").toString(),
                    color = Color(104, 104, 104),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "${
                        localStorage.readDataString(context, "cidade_cliente").toString()
                    } ${localStorage.readDataString(context, "estado_cliente").toString()}",
                    color = Color(104, 104, 104),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(15.dp))



                Text(
                    text = "Avaliação",
                    fontSize = 20.sp,
                    color = Color(41, 95, 27)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(3.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(3.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)

                        )

                        Spacer(modifier = Modifier.width(3.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    }



                    Text(
                        text = "Enviar",
                        fontSize = 15.sp,
                        color = Color(41, 95, 27)
                    )
                }


            }

        }

    }
}


fun calculoTotal(
    subtotal: Float,
    frete: Float
): String {
    val resultado = subtotal + frete
    return String.format(Locale.getDefault(), "%.2f", resultado)
}