package br.senai.sp.saveeats.restaurantprofile.screen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.AddressRestaurant
import br.senai.sp.saveeats.model.AddressRestaurantList
import br.senai.sp.saveeats.model.Avaliation
import br.senai.sp.saveeats.model.AvaliationList
import br.senai.sp.saveeats.model.HorarioDeFuncionamento
import br.senai.sp.saveeats.model.HorarioDeFuncionamentoList
import br.senai.sp.saveeats.model.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Response

@Composable
fun RestaurantProfile(
    navController: NavController,
    localStorage: Storage
) {


    val context = LocalContext.current

    var iconeState by remember {
        mutableStateOf(false)
    }

    var progressState by remember {
        mutableStateOf(true)
    }

    var adressRestaurant by remember {
        mutableStateOf(
            listOf<AddressRestaurant>()
        )
    }

    var listAvaliation by remember {
        mutableStateOf(
            listOf<Avaliation>()
        )
    }

    var listHorarioDeFucionamento by remember {
        mutableStateOf(
            listOf<HorarioDeFuncionamento>()
        )
    }

    var numeroAvaliacoes by remember {
        mutableStateOf(0)
    }

    var mediaRestaurante by remember {
        mutableStateOf("0")
    }


    val nameRestaurant = localStorage.readDataString(context, "nameRestaurant")
    val imageRestaurant = localStorage.readDataString(context, "imageRestaurant")
    val nameCategoryRestaurant = localStorage.readDataString(context, "nameCategoryRestaurant")
    val idRestaurant = localStorage.readDataInt(context, "idRestaurant")


    val callAvaliation = RetrofitFactory
        .getRestaurantRate()
        .getRestaurantRate(idRestaurant)

    callAvaliation.enqueue(object : retrofit2.Callback<AvaliationList> {

        override fun onResponse(
            call: Call<AvaliationList>,
            response: Response<AvaliationList>
        ) {
            mediaRestaurante = response.body()!!.media_estrelas
            numeroAvaliacoes = response.body()!!.quantidade_avaliacoes
            listAvaliation = response.body()!!.avaliacoes_do_restaurante
        }

        override fun onFailure(
            call: Call<AvaliationList>,
            t: Throwable
        ) {
            Log.e("TESTE2", "onFailure: ${t.message}")
        }


    })


    val callRestaurantAdress = RetrofitFactory
        .getAdressByIdRestaurant()
        .getAdressRestaurantByID(idRestaurant)

    callRestaurantAdress.enqueue(object : retrofit2.Callback<AddressRestaurantList> {

        override fun onResponse(
            call: Call<AddressRestaurantList>,
            response: Response<AddressRestaurantList>
        ) {
            adressRestaurant = response.body()!!.endereco_restaurante
        }

        override fun onFailure(
            call: Call<AddressRestaurantList>,
            t: Throwable
        ) {
            Log.e("TESTE2", "onFailure: ${t.message}")
        }


    })


    val callHorarioDeFuncionamento = RetrofitFactory
        .getHorarioDeFuncionamentoByIdRestaurante()
        .getHorarioDeFuncionamentoByIdRestaurante(1)

    callHorarioDeFuncionamento.enqueue(object : retrofit2.Callback<HorarioDeFuncionamentoList> {
        override fun onResponse(
            call: Call<HorarioDeFuncionamentoList>,
            response: Response<HorarioDeFuncionamentoList>
        ) {
            listHorarioDeFucionamento = response.body()!!.dias_horarios_funcionamento
        }

        override fun onFailure(
            call: Call<HorarioDeFuncionamentoList>,
            t: Throwable
        ) {
            Log.e("ERROR", "${t.message}")
        }
    })








    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {


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
                if (!iconeState) {
                    painterResource(id = R.drawable.baseline_favorite_border_24)
                } else {
                    painterResource(id = R.drawable.baseline_favorite_24)
                },
                contentDescription = "",
                tint =
                if (!iconeState) {
                    Color(71, 71, 71, 255)
                } else {
                    Color(255, 0, 0)
                },
                modifier = Modifier
                    .clickable {
                        iconeState = iconeState != true
                    }
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = nameRestaurant.toString(),
                    color = Color(0, 0, 0),
                    fontWeight = FontWeight(600),
                    fontSize = 25.sp
                )
                Text(
                    text = nameCategoryRestaurant.toString(),
                    color = Color(0, 0, 0),
                    fontWeight = FontWeight(300),
                    fontSize = 12.sp
                )
            }

            AsyncImage(
                model = imageRestaurant,
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(
                text = mediaRestaurante,
                color = Color(255, 200, 0, 255),
                fontWeight = FontWeight(400),
                fontSize = 15.sp
            )
            
            Spacer(modifier = Modifier.width(10.dp))

            Row (
                modifier = Modifier
                    .width(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    tint =
                    if (mediaRestaurante.replace(",", ".").toDouble() >= 1.0) {
                        Color(255, 200, 0, 255)
                    } else {
                        Color(117, 117, 117, 255)
                    },
                    modifier = Modifier.size(16.dp)
                )
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    tint =
                    if (mediaRestaurante.replace(",", ".").toDouble() >= 2.0) {
                        Color(255, 200, 0, 255)
                    } else {
                        Color(117, 117, 117, 255)
                    },
                    modifier = Modifier.size(16.dp)
                )
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    tint =
                    if (mediaRestaurante.replace(",", ".").toDouble() >= 3.0) {
                        Color(255, 200, 0, 255)
                    } else {
                        Color(117, 117, 117, 255)
                    },
                    modifier = Modifier.size(16.dp)
                )
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    tint =
                    if (mediaRestaurante.replace(",", ".").toDouble() >= 4.0) {
                        Color(255, 200, 0, 255)
                    } else {
                        Color(117, 117, 117, 255)
                    },
                    modifier = Modifier.size(16.dp)
                )
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    tint =
                    if (mediaRestaurante.replace(",", ".").toDouble() >= 5.0) {
                        Color(255, 200, 0, 255)
                    } else {
                        Color(117, 117, 117, 255)
                    },
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Text(
            text = "${numeroAvaliacoes} avaliações",
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


                if (numeroAvaliacoes == 0) {

                    Text(text = "Nenhuma avaliação")


                } else {


                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(listAvaliation) {


                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)

                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically


                                ) {
                                    if (it.foto_cliente == ""){
                                        Image(
                                            painter = painterResource(id = R.drawable.perfil),
                                            contentDescription = "Profile Photo",
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop

                                        )
                                    }else{
                                        AsyncImage(
                                            model = it.foto_cliente,
                                            contentDescription = "Profile Photo",
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(5.dp))

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = it.nome_cliente,
                                            color = Color(0, 0, 0),
                                            fontWeight = FontWeight(600),
                                            fontSize = 15.sp
                                        )

                                        Row(
                                            verticalAlignment = Alignment
                                                .CenterVertically,
                                            modifier = Modifier
                                                .width(150.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {

                                            Text(
                                                text = "${it.quantidade_estrela}",
                                                color = Color(255, 215, 0, 255),
                                                fontWeight = FontWeight(500),
                                                fontSize = 14.sp
                                            )

                                            Spacer(modifier = Modifier.width(5.dp))

                                            Icon(
                                                painter = painterResource(id = R.drawable.star),
                                                contentDescription = "",
                                                tint = if (it.quantidade_estrela >= 1) {
                                                    Color(255, 215, 0, 255)
                                                } else {
                                                    Color(117, 117, 117, 255)
                                                },
                                                modifier = Modifier
                                                    .size(20.dp)
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.star),
                                                contentDescription = "",
                                                tint = if (it.quantidade_estrela >= 2) {
                                                    Color(255, 215, 0, 255)
                                                } else {
                                                    Color(117, 117, 117, 255)
                                                },
                                                modifier = Modifier
                                                    .size(20.dp)
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.star),
                                                contentDescription = "",
                                                tint = if (it.quantidade_estrela >= 3) {
                                                    Color(255, 215, 0, 255)
                                                } else {
                                                    Color(117, 117, 117, 255)
                                                },
                                                modifier = Modifier
                                                    .size(20.dp)
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.star),
                                                contentDescription = "",
                                                tint = if (it.quantidade_estrela >= 4) {
                                                    Color(255, 215, 0, 255)
                                                } else {
                                                    Color(117, 117, 117, 255)
                                                },
                                                modifier = Modifier
                                                    .size(20.dp)
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.star),
                                                contentDescription = "",
                                                tint = if (it.quantidade_estrela >= 5) {
                                                    Color(255, 215, 0, 255)
                                                } else {
                                                    Color(117, 117, 117, 255)
                                                },
                                                modifier = Modifier
                                                    .size(20.dp)
                                            )

                                        }


                                        Text(
                                            text = it.avaliacao_descricao,
                                            color = Color(99, 99, 99, 255),
                                            fontWeight = FontWeight(400),
                                            fontSize = 13.sp,
                                            lineHeight = 14.sp
                                        )

                                    }
                                }

                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(194, 194, 194, 255))


                            ) {

                            }

                        }
                    }
                }
            }


        } else {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .absoluteOffset(y = 20.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_place_24),
                            contentDescription = "",
                            tint = Color(41, 91, 27),
                            modifier = Modifier
                                .size(23.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = "Endereço",
                            color = Color(0, 0, 0),
                            fontWeight = FontWeight(600),
                            fontSize = 20.sp
                        )
                    }
                    Column() {
                        Text(
                            text = "${adressRestaurant[0].rua},${adressRestaurant[0].numero}",
                            color = Color(0, 0, 0),
                            fontSize = 15.sp
                        )

                        Text(
                            text = "CEP - ${adressRestaurant[0].cep}",
                            color = Color(0, 0, 0),
                            fontSize = 15.sp
                        )


                    }
                }

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_watch_later_24),
                            contentDescription = "",
                            tint = Color(41, 91, 27),
                            modifier = Modifier
                                .size(23.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = "Horários de Funcionamento",
                            color = Color(0, 0, 0),
                            fontWeight = FontWeight(600),
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(listHorarioDeFucionamento) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = it.dia_semana)
                                Text(text = "${it.horario_inicio} as ${it.horario_final}")

                            }
                        }
                    }
                }

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_attach_money_24),
                            contentDescription = "",
                            tint = Color(41, 91, 27),
                            modifier = Modifier
                                .size(23.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = stringResource(id = R.string.payment_methods),
                            color = Color(0, 0, 0),
                            fontWeight = FontWeight(600),
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Formas de pagamento aceitas",
                            color = Color(0, 0, 0),
                            fontSize = 15.sp
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.baseline_pix_24),
                            contentDescription = "Forma de pagamento",
                            tint = Color(6, 189, 174),
                            modifier = Modifier.size(25.dp)
                        )


                    }
                }


            }
        }

    }


}

