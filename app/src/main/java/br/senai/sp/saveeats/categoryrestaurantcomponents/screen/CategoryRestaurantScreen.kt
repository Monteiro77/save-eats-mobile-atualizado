package br.senai.sp.saveeats.categoryrestaurantcomponents.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.model.CategoryRestaurant
import br.senai.sp.saveeats.model.CategoryRestaurantList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRestaurantScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getCategoryRestaurant = intent.getStringExtra("name_category")

        setContent {
            SaveEatsTheme {

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CategoryRestaurantScreen(
                        getCategoryRestaurant.toString(),
                        navController
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryRestaurantScreen(nameCategory: String, navController: NavController) {

    var listCategoryRestaurant by remember {
        mutableStateOf(listOf<CategoryRestaurant>())
    }

    //cria uma chamada para o endpoint
    var callCategoryRestaurant = RetrofitFactory
        .getCategoryRestaurant()
        .getCategoryRestaurant(nameCategory)

    callCategoryRestaurant.enqueue(object : Callback<CategoryRestaurantList> {
        override fun onResponse(
            call: Call<CategoryRestaurantList>,
            response: Response<CategoryRestaurantList>
        ) {
            listCategoryRestaurant = response.body()!!.restaurantes_da_categoria_escolhida
        }

        override fun onFailure(
            call: Call<CategoryRestaurantList>,
            t: Throwable
        ) {

            Log.i("ds3t", "onFailure: ${t.message}")

        }

    })

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .offset(x = 30.dp, y = 58.dp)
            ) {

                IconButton(
                    modifier = Modifier
                        .size(20.dp),
                    onClick = {
                        navController.navigate("home_screen")
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Arrow",
                        tint = Color(76,132,62)
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
                    text = nameCategory.uppercase(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400
                )

            }

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(listCategoryRestaurant) {

                Surface(
                    modifier = Modifier
                        .width(350.dp)
                        .height(60.dp)
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        if (it.foto == "") {

                            Image(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(100.dp)),
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = ""
                            )

                        } else {

                            AsyncImage(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(100.dp)),
                                model = it.foto,
                                contentDescription = "Image Restaurant"
                            )

                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {

                            Text(
                                text = it.nome_fantasia,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(
                                    modifier = Modifier
                                        .size(15.dp),
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = "Star"
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = "4,8",
                                    fontSize = 13.sp,
                                    color = Color(252, 187, 0)
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Image(
                                    modifier = Modifier
                                        .size(20.dp),
                                    painter = painterResource(id = R.drawable.pointer),
                                    contentDescription = "Pointer"
                                )

                                Text(
                                    text = "Hortifruti",
                                    fontSize = 13.sp
                                )

                            }

                        }

                    }

                }

            }

        }

    }

}