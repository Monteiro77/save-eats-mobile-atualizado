package br.senai.sp.saveeats.restaurantprofile.screen

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.RatingRestaurantList
import br.senai.sp.saveeats.model.RestaurantRate
import br.senai.sp.saveeats.model.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

@Composable
fun RestaurantProfile(
    navController: NavController,
    localStorage: Storage
) {

    val context = LocalContext.current
    val idRestaurant = localStorage.readDataInt(context, "idRestaurant")

    var quantidadeAvaliacoes by remember {
        mutableIntStateOf(0)
    }

    var listResuranteRate by remember {
        mutableStateOf(listOf<RestaurantRate>())
    }

    val callRestaurantRate = RetrofitFactory
        .getRestaurantRate()
        .getRestaurantRate(idRestaurant)

    callRestaurantRate.enqueue(object : retrofit2.Callback<RatingRestaurantList>{
        override fun onResponse(
            call: Call<RatingRestaurantList>,
            response: Response<RatingRestaurantList>
        ) {
            listResuranteRate = response.body()!!.avaliacoes_do_restaurante
        }

        override fun onFailure(call: Call<RatingRestaurantList>, t: Throwable) {
            Log.e("ERROR", "onFailure: $t", )
        }
    })
    Surface() {
        
        LazyColumn{
            items(listResuranteRate){
                Text(text = it.nome_cliente)
            }
        }

    }

}