package br.senai.sp.saveeats.avaliationcomponents.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
//import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.AvaliationRepository
import br.senai.sp.saveeats.model.Recomendation
import br.senai.sp.saveeats.model.RecomendationsList
import br.senai.sp.saveeats.model.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvaliationScreen(
    navController: NavController,
//    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope
) {
    val context = LocalContext.current


//    val idClient = localStorage.readDataInt(context, "idClient")
//    val idRestaurant = localStorage.readDataInt(context, "idRestaurant")

    val sdf = SimpleDateFormat("yyyy/MM/dd")

    val currentDate = sdf.format(Date())


    var listRecomendation by remember {
        mutableStateOf(
            listOf<Recomendation>()

        )
    }

    var avaliationState by remember {
        mutableStateOf("")
    }

    var quantidadeEstrela by remember {
        mutableIntStateOf(0)
    }

    var idRecomendacao by remember {
        mutableIntStateOf(0)
    }

    // GET RECOMEDAÇÕES
    val callRecomendation = RetrofitFactory
        .getRecomendation()
        .getRecomendacoes()

    callRecomendation.enqueue(object : retrofit2.Callback<RecomendationsList> {
        override fun onResponse(
            call: Call<RecomendationsList>,
            response: Response<RecomendationsList>
        ) {
            listRecomendation = response.body()!!.recomendacoes

        }

        override fun onFailure(call: Call<RecomendationsList>, t: Throwable) {
            Log.e("Error", "onFailure: $t")
        }


    })

    //POST AVALIACAO

    fun avaliation(
        idCliente: Int,
        idRestaurante: Int,
        quantidadeEstrela: Int,
        descricao: String,
        dataAvaliacao: String,
        recomendacaoId: Int
    ) {

        val avaliar = AvaliationRepository()

        lifecycleScope.launch {
            val response = avaliar.createAvaliation(
                idCliente,
                idRestaurante,
                quantidadeEstrela,
                descricao,
                dataAvaliacao,
                recomendacaoId
            )

            if (response.isSuccessful) {
                Toast.makeText(context, "Avaliado com sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Log.e("ERROR", "$response")
            }


        }


    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {

        Row(
            modifier = Modifier
                .width(240.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Arrow Back",
                tint = Color(51, 114, 37, 255),
                modifier = Modifier
                    .clickable {
                        navController
                            .popBackStack()
                    }
            )

            Text(
                text = "Avaliação",
                color = Color(32, 90, 32, 255),
                fontSize = 22.sp,
                fontWeight = FontWeight(400)
            )


        }

        Spacer(modifier = Modifier.height(15.dp))



        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            colors = CardDefaults.cardColors(
                Color(32, 90, 32, 255)
            )
        ) {}

        Text(
            text = "Oque você achou do seu pedido?",
            color = Color(0, 0, 0, 255),
            fontSize = 20.sp,
            fontWeight = FontWeight(500)
        )

        Text(
            text = "Escolha de 1 a 5 estrelas",
            color = Color(0, 0, 0, 255),
            fontSize = 16.sp,
            fontWeight = FontWeight(300)
        )

        Row(
            modifier = Modifier
                .width(120.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        quantidadeEstrela = 1
                    },
                tint = if (quantidadeEstrela < 1) {
                    Color(126, 126, 126, 255)
                } else {
                    Color(255, 229, 0, 255)
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { quantidadeEstrela = 2 },
                tint = if (quantidadeEstrela < 2) {
                    Color(126, 126, 126, 255)
                } else {
                    Color(255, 229, 0, 255)
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { quantidadeEstrela = 3 },
                tint = if (quantidadeEstrela < 3) {
                    Color(126, 126, 126, 255)
                } else {
                    Color(255, 229, 0, 255)
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { quantidadeEstrela = 4 },
                tint = if (quantidadeEstrela < 4) {
                    Color(126, 126, 126, 255)
                } else {
                    Color(255, 229, 0, 255)
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { quantidadeEstrela = 5 },
                tint = if (quantidadeEstrela < 5) {
                    Color(126, 126, 126, 255)
                } else {
                    Color(255, 229, 0, 255)
                }
            )

        }

        Text(
            text = "Oque podemos melhorar?",
            fontSize = 15.sp
        )



        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 10.dp
        ) {

            items(listRecomendation) {

                Card(
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp)
                        .clickable {
                            idRecomendacao = it.id
                        },
                    colors = CardDefaults.cardColors(
                        if (idRecomendacao == it.id) {
                            Color(41, 95, 27)
                        } else {
                            Color(214, 215, 216)
                        }
                    ),
                    shape = RoundedCornerShape(80)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Text(
                            text = it.recomendacao,
                            color = Color.White
                        )
                    }

                }
            }
        }

        OutlinedTextField(
            value = avaliationState,
            onValueChange = {
                avaliationState = it
            },
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            label = {
                Text(
                    text = "Digite seu comentário"
                )
            }
        )

        Button(
            onClick = {
                avaliation(
                    6,
                    1,
                    quantidadeEstrela,
                    avaliationState,
                    currentDate,
                    idRecomendacao
                )
            }
        ) {

            Text(text = "Avaliar")

        }

    }

//


}