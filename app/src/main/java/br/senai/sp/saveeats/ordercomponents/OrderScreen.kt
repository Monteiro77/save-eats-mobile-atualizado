package br.senai.sp.saveeats.ordercomponents.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.HistoricoCliente
import br.senai.sp.saveeats.model.OrderList
import br.senai.sp.saveeats.model.ProductHistoricoList
import br.senai.sp.saveeats.model.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Response

@Composable
fun OrderScreen(
    navController: NavController,
    navController2: NavController,
    localStorage: Storage
) {
    var context = LocalContext.current

    var idClient = localStorage.readDataInt(context, "idClient")


    var listProducts by remember {
        mutableStateOf(
            listOf<ProductHistoricoList>()
        )
    }

    var listOrders by remember {
        mutableStateOf(
            listOf<OrderList>()
        )
    }

    var callHistorico = RetrofitFactory
        .getHistoricoById()
        .getHistoricoById(idClient)


    callHistorico.enqueue(object : retrofit2.Callback<HistoricoCliente> {
        override fun onResponse(
            call: Call<HistoricoCliente>,
            response: Response<HistoricoCliente>
        ) {
            var listHistorico = response.body()?.detalhes_do_pedido_do_cliente?.get(0)

        }

        override fun onFailure(
            call: Call<HistoricoCliente>,
            t: Throwable
        ) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Histórico de Pedidos",
            fontSize = 22.sp

        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(20, 58, 11))
                .height(2.dp),


            ) {

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {


            items(listOrders) {

                Spacer(modifier = Modifier.height(25.dp))


                Row() {
                    Text(
                        text = it.data_pedido.toString(),
                        color = Color(104, 104, 104),
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))



                Card(
                    modifier = Modifier
                        .width(350.dp)
                        .height(147.dp)
                        .shadow(
                            elevation = 8.dp,
                            spotColor = Color(0xFF000000),
                            ambientColor = Color(0xFF000000),
                            shape = RoundedCornerShape(30.dp)
                        ), colors = CardDefaults.cardColors(
                        Color(212, 227, 204)
                    ), shape = RoundedCornerShape(30.dp)

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            AsyncImage(
                                model = it.foto_restaurante,
                                contentDescription = "Imagem do Restuarnte",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(15.dp))

                            Text(
                                text = it.nome_restaurante.toString(),
                                fontSize = 20.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Card(
                            modifier = Modifier
                                .width(270.dp)
                                .height(1.5.dp),
                            colors = CardDefaults.cardColors(
                                Color(255, 141, 6)
                            )
                        ) {

                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row {
                            if (it.status_pedido == "Pedido Concluído") {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_verified_24),
                                    contentDescription = "",
                                    tint = Color(72, 138, 39),
                                    modifier = Modifier.size(15.dp)
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = it.status_pedido,
                                    color = Color(104, 104, 104),
                                    fontSize = 10.sp
                                )

                                Text(
                                    text = it.numero_pedido.toString(),
                                    color = Color(104, 104, 104),
                                    fontSize = 10.sp
                                )

                            }else{
                                Text(
                                    text = it.status_pedido.toString(),
                                    color = Color(104, 104, 104),
                                    fontSize = 10.sp
                                )

                                Text(
                                    text = it.numero_pedido.toString(),
                                    color = Color(104, 104, 104),
                                    fontSize = 10.sp
                                )
                            }
                        }

//                        Row() {
//                            Icon(
//                                painter = painterResource(id = R.drawable.baseline_verified_24),
//                                contentDescription = "",
//                                tint = Color(72, 138, 39),
//                                modifier = Modifier.size(15.dp)
//                            )
//
//                            Spacer(modifier = Modifier.width(4.dp))
//
//                            Text(
//                                text = "Pedido concluído Nº 7800",
//                                color = Color(104, 104, 104),
//                                fontSize = 10.sp
//                            )
//                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {
                                navController2.navigate("detalhes_pedido_screen")
                            },
                            colors = ButtonDefaults.buttonColors(
                                Color(72, 138, 39)
                            )
                        ) {
                            Text(
                                text = "Detalhes do Pedido"
                            )
                        }

                    }
                }


            }
        }
    }

}


