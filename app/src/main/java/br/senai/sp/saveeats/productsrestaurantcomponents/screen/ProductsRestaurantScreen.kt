package br.senai.sp.saveeats.productsrestaurantcomponents.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.ProductsRestaurant
import br.senai.sp.saveeats.model.ProductsRestaurantList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.productcomponents.screen.ProductScreen
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRestaurantScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getNameRestaurant = intent.getStringExtra("name_restaurant")

        setContent {
            SaveEatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductsRestaurantScreen(
                        getNameRestaurant.toString(),
                        localStorage = Storage()
                    )
                }
            }
        }
    }
}

@Composable
fun ProductsRestaurantScreen(nameRestaurant: String, localStorage: Storage) {

    var context = LocalContext.current

    var imageRestaurante = localStorage.readDataString(context, "imageRestaurant")

    var nameCategoryRestaurant = localStorage.readDataString(context, "nameCategoryRestaurant")

    var listProductsRestaurant by remember {
        mutableStateOf(listOf<ProductsRestaurant>())
    }

    //cria uma chamada para o endpoint
    var callProductsRestaurant = RetrofitFactory
        .getProductsRestaurant()
        .getProductsRestaurant(nameRestaurant)

    callProductsRestaurant.enqueue(object : Callback<ProductsRestaurantList> {
        override fun onResponse(
            call: Call<ProductsRestaurantList>,
            response: Response<ProductsRestaurantList>
        ) {

//            if (response.body()!!. == 404) {
//                listProductsRestaurant = emptyList()
//            } else {
//                listProductsRestaurant = response.body()!!.produtos_do_restaurante
//            }

        }

        override fun onFailure(
            call: Call<ProductsRestaurantList>,
            t: Throwable
        ) {

            Log.i("ds3t", "onFailure: ${t.message}")

        }

    })

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp),
                model = imageRestaurante,
                contentDescription = "Image Background"
            )

            Column(
                modifier = Modifier
                    .absoluteOffset(x = -(80).dp)
                    .padding(start = 40.dp)
            ) {

                Text(
                    text = nameRestaurant,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W600,
                    color = Color(20, 34, 27)
                )

                Text(
                    text = nameCategoryRestaurant.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W300,
                    color = Color(85, 85, 85)
                )

                Text(
                    text = "Resutant profile",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600,
                    color = Color(41, 95, 27)
                )

            }

            Row(
                modifier = Modifier
                    .padding(top = 15.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Star"
                )

                Text(
                    text = "4,5",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(255, 193, 7)
                )

            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {

            Text(
                modifier = Modifier
                    .padding(start = 15.dp),
                text = "Menu",
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp
            )

        }

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(listProductsRestaurant) {

                Card(
                    modifier = Modifier
                        .height(110.dp)
                        .clickable {
                            var openProduct = Intent(context, ProductScreen::class.java)
                            openProduct.putExtra("imageProduct", it.imagem)
                            openProduct.putExtra("nameProduct", it.nome)
                            openProduct.putExtra("priceProduct", it.preco)
                            openProduct.putExtra("descriptionProduct", it.descricao)
                            context.startActivity(openProduct)
                        },
                    colors = CardDefaults.cardColors(colorResource(id = R.color.white))
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Card(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20))
                                .height(80.dp)
                                .width(80.dp)
                                .shadow(
                                    elevation = 4.dp,
                                    spotColor = Color(0x40000000),
                                    ambientColor = Color(0x40000000)
                                ),
                            colors = CardDefaults.cardColors(colorResource(id = R.color.white))
                        ) {

                            AsyncImage(
                                modifier = Modifier
                                    .size(300.dp),
                                model = it.imagem,
                                contentDescription = "Image Product"
                            )

                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp),
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = it.nome,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W400
                            )

                            Text(
                                text = it.descricao,
                                fontWeight = FontWeight.W300,
                                letterSpacing = 1.sp
                            )

                            Text(
                                text = "R$ ${it.preco}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W600,
                                color = Color(20, 58, 11)
                            )

                        }

                    }

                }

            }

        }

    }

}