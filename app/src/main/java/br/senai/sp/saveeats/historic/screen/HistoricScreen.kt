package br.senai.sp.saveeats.historic.screen

import androidx.compose.runtime.Composable
import android.util.Log
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.Historic
import br.senai.sp.saveeats.model.HistoricList
import br.senai.sp.saveeats.model.RetrofitFactory
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import retrofit2.Call
import retrofit2.Response

@Composable
fun HistoricScreen(
    navController2: NavController,
    localStorage: Storage
) {

    val waitingAnimation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.waiting_animation))

    val verifierAnimation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.verified_animation))


    val context = LocalContext.current

    val idClient = localStorage.readDataInt(context, "idClient")

    var listOrders by remember {
        mutableStateOf(
            listOf<Historic>()
        )
    }


    val callHistorico = RetrofitFactory
        .getHistoricById()
        .getHistoricByIdClient(idClient)

    callHistorico.enqueue(object : retrofit2.Callback<HistoricList> {
        override fun onResponse(
            call: Call<HistoricList>,
            response: Response<HistoricList>
        ) {
                listOrders = response.body()!!.detalhes_do_pedido_do_cliente
        }

        override fun onFailure(
            call: Call<HistoricList>,
            t: Throwable
        ) {
            Log.e("TESTE2", "onFailure: ${t.message}")
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

        Spacer(modifier = Modifier.height(20.dp))


        if(listOrders.isNullOrEmpty()){
            Column {
                Text(text = "SEM AVALIAÇÕES")
            }
        }else{

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(1),
                verticalItemSpacing = 15.dp
            ) {

                items(listOrders) {

                    Card(
                        modifier = Modifier
                            .width(350.dp)
                            .height(170.dp)
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
                                    .padding(horizontal = 10.dp)
                            ) {
                                Text(
                                    text = it.data_pedido,
                                    color = Color(104, 104, 104)
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                AsyncImage(
                                    model = it.foto_restaurante,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.width(15.dp))

                                Text(
                                    text = it.nome_restaurante, fontSize = 20.sp
                                )

                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                modifier = Modifier
                                    .height(2.dp)
                                    .width(300.dp)
                                    .background(Color(153, 153, 153, 180))
                                    .clip(CircleShape)

                            ) {}

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                if (it.status_pedido == "Pedido Finalizado" || it.status_pedido == "Pedido entregue" || it.status_pedido.length == 15) {

                                    LottieAnimation(
                                        composition = verifierAnimation,
                                        modifier = Modifier.size(20.dp),
                                        iterations = LottieConstants.IterateForever
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = it.status_pedido,
                                        color = Color(104, 104, 104),
                                        fontSize = 10.sp
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = it.numero_pedido,
                                        color = Color(104, 104, 104),
                                        fontSize = 10.sp
                                    )

                                } else {


                                    LottieAnimation(
                                        composition = waitingAnimation,
                                        modifier = Modifier.size(25.dp),
                                        iterations = LottieConstants.IterateForever
                                    )


                                    Text(
                                        text = it.status_pedido,
                                        color = Color(104, 104, 104),
                                        fontSize = 10.sp
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = it.numero_pedido,
                                        color = Color(104, 104, 104),
                                        fontSize = 10.sp
                                    )


                                }
                            }

                            Button(
                                onClick = {
                                    navController2.navigate("detalhes_pedido_historico_screen")
                                    localStorage.saveDataString(
                                        context,
                                        it.foto_restaurante,
                                        "imageRestaurant"
                                    )
                                    localStorage.saveDataInt(context, it.id_pedido, "idOrder")
                                    localStorage.saveDataString(
                                        context,
                                        it.foto_restaurante,
                                        "foto_restaurante"
                                    )
//                                localStorage.saveDataString(context, it.estado_cliente, "estado_cliente")
                                    localStorage.saveDataString(
                                        context,
                                        it.bairro_cliente,
                                        "bairro_cliente"
                                    )
                                    localStorage.saveDataString(
                                        context,
                                        it.cep_cliente,
                                        "cep_cliente"
                                    )
//                                localStorage.saveDataString(context, it.cidade_cliente, "cidade_cliente")
                                },
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(30.dp),
                                colors = ButtonDefaults.buttonColors(
                                    Color(72, 138, 39)
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.details_orders),
                                        color = Color.White,
                                        fontSize = 10.sp
                                    )

                                }

                            }


                        }
                    }
                }

            }
        }
    }

}