package br.senai.sp.saveeats.homecomponents.screen

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.productsrestaurantcomponents.screen.ProductsRestaurantScreen
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.categoryrestaurantcomponents.screen.CategoryRestaurantScreen
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.components.SearchOutlineTextField
import br.senai.sp.saveeats.model.Category
import br.senai.sp.saveeats.model.CategoryList
import br.senai.sp.saveeats.model.ClientAddress
import br.senai.sp.saveeats.model.ClientAddressList
import br.senai.sp.saveeats.model.RecipeDetails
import br.senai.sp.saveeats.model.RestaurantList
import br.senai.sp.saveeats.model.Restaurant
import br.senai.sp.saveeats.viewmodel.RestaurantViewModel
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    lifecycle: LifecycleCoroutineScope,
    localStorage: Storage
) {

    var context = LocalContext.current

    val imageSlide = remember {
        mutableStateListOf(
            R.drawable.paes,
            R.drawable.restaurante,
            R.drawable.livro_de_receitas
        )
    }

    val textSlide = remember {
        mutableStateListOf(
            R.string.slide_one,
            R.string.slide_two,
            R.string.slide_three
        )
    }

    val colorsSlide = remember {
        mutableStateListOf(
            R.color.slide_one,
            R.color.slide_two,
            R.color.slide_three,
        )
    }

    val pagerState = rememberPagerState(
        pageCount = {
            3
        }
    )

    var listCategory by remember {
        mutableStateOf(listOf<Category>())
    }

    var listRestaurant by remember {
        mutableStateOf(listOf<Restaurant>())
    }

    var listClientAddress by remember {
        mutableStateOf(
            listOf(
                ClientAddress()
            )
        )
    }

    //API CATEGORY - START

    var callCategory = RetrofitFactory
        .getCategory()
        .getCategory()

    callCategory.enqueue(object : Callback<CategoryList> {
        override fun onResponse(
            call: Call<CategoryList>,
            response: Response<CategoryList>
        ) {
            listCategory = response.body()!!.categorias
        }

        override fun onFailure(
            call: Call<CategoryList>,
            t: Throwable
        ) {

            Log.i("ds3t", "onFailure: ${t.message}")

        }

    })

    //API CATEGORY - END

    //API RESTAURANT - START

    var callRestaurant = RetrofitFactory
        .getRestaurant()
        .getRestaurantCall()

    callRestaurant.enqueue(object : Callback<RestaurantList> {
        override fun onResponse(
            call: Call<RestaurantList>,
            response: Response<RestaurantList>
        ) {
            listRestaurant = response.body()!!.restaurantes
        }

        override fun onFailure(
            call: Call<RestaurantList>,
            t: Throwable
        ) {

            Log.i("ds3t", "onFailure: ${t.message}")

        }

    })

    //API RESTAURANT - END

    //API CLIENT ADDRESS - START

    val idClient = localStorage.readDataInt(context, "idClient")

    var callClientAddress = RetrofitFactory
        .getAddressClient()
        .getAddressClient(idClient)

    callClientAddress.enqueue(object : Callback<ClientAddressList> {
        override fun onResponse(
            call: Call<ClientAddressList>,
            response: Response<ClientAddressList>
        ) {
            listClientAddress = response.body()!!.endereco_cliente
            Log.e("TESTE3", "onResponse: $listClientAddress")
        }

        override fun onFailure(
            call: Call<ClientAddressList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

        }

    })

    //API CLIENT ADDRESS - END

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier
                        .size(60.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo"
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .size(25.dp),
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Location",
                        tint = Color(76, 132, 62)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Row {

                        Text(
                            text = "${listClientAddress[0].rua_cliente}",
                            fontSize = 17.sp

                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "${listClientAddress[0].numero_endereco_cliente},",
                            fontSize = 17.sp

                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "${listClientAddress[0].nome_cidade}",
                            fontSize = 17.sp

                        )

                    }

                }

                IconButton(onClick = {
                    navController.navigate("")
                }

                ) {

                    Icon(
                        modifier = Modifier
                            .size(25.dp),
                        painter = painterResource(id = R.drawable.carrinho),
                        contentDescription = "Carrinho de Compras",
                        tint = Color(76, 132, 62)
                    )

                }

            }

            Column(
                modifier = Modifier
            ) {

                SearchOutlineTextField(
                    lifecycleScope = lifecycle,
                    viewModel = RestaurantViewModel()
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    modifier = Modifier
                        .padding(start = 25.dp, bottom = 10.dp),
                    text = stringResource(id = R.string.categories),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(20, 58, 11),
                    letterSpacing = 2.sp
                )

                LazyRow(
                    modifier = Modifier
                        .padding(start = 25.dp)
                ) {

                    items(listCategory) {

                        Card(
                            modifier = Modifier
                                .width(200.dp)
                                .height(45.dp)
                                .padding(end = 15.dp)
                                .clickable {
                                    var openCategoryRestaurant =
                                        Intent(context, CategoryRestaurantScreen()::class.java)
                                    openCategoryRestaurant.putExtra(
                                        "name_category",
                                        it.nome_categoria
                                    )
                                    context.startActivity(openCategoryRestaurant)
                                },
                            border = BorderStroke(0.8.dp, Color(212, 212, 212)),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )

                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                AsyncImage(
                                    modifier = Modifier
                                        .size(35.dp),
                                    model = it.img_categoria,
                                    contentDescription = "Image Category"
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    modifier = Modifier
                                        .width(85.dp),
                                    text = it.nome_categoria,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black
                                )

                            }

                        }

                    }

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    modifier = Modifier
                        .width(360.dp)
                        .height(160.dp)
                ) {

                    HorizontalPager(
                        state = pagerState,
                        pageSpacing = 0.dp,
                        pageContent = {

                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(colorResource(id = colorsSlide[it])),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    modifier = Modifier
                                        .width(160.dp),
                                    text = stringResource(id = textSlide[it]),
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                )

                                Image(
                                    modifier = Modifier
                                        .size(120.dp),
                                    painter = painterResource(id = imageSlide[it]),
                                    contentDescription = ""
                                )

                            }

                        }

                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Card(
                    modifier = Modifier
                        .width(18.dp)
                        .height(12.dp),
                    colors =

                    if (pagerState.currentPage == 0) {

                        CardDefaults.cardColors(
                            containerColor = Color(76, 132, 62)
                        )

                    } else {

                        CardDefaults.cardColors(
                            containerColor = Color(217, 217, 217)
                        )
                    }

                ) {}

                Spacer(modifier = Modifier.width(25.dp))

                Card(
                    modifier = Modifier
                        .width(18.dp)
                        .height(12.dp),
                    colors =

                    if (pagerState.currentPage == 1) {

                        CardDefaults.cardColors(
                            containerColor = Color(76, 132, 62)
                        )

                    } else {

                        CardDefaults.cardColors(
                            containerColor = Color(217, 217, 217)
                        )

                    }

                ) {}

                Spacer(modifier = Modifier.width(25.dp))

                Card(
                    modifier = Modifier
                        .width(18.dp)
                        .height(12.dp),
                    colors =

                    if (pagerState.currentPage == 2) {

                        CardDefaults.cardColors(
                            containerColor = Color(76, 132, 62)
                        )

                    } else {

                        CardDefaults.cardColors(
                            containerColor = Color(217, 217, 217)
                        )

                    }

                ) {}

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    modifier = Modifier
                        .padding(start = 25.dp, bottom = 10.dp, top = 20.dp),
                    text = stringResource(id = R.string.restaurant),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(20, 58, 11),
                    letterSpacing = 2.sp
                )

            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 45.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                items(listRestaurant) {

                    Surface(
                        modifier = Modifier
                            .width(350.dp)
                            .height(60.dp)
                            .padding(bottom = 10.dp)
                            .clickable {

                                localStorage.saveDataString(context, it.foto, "imageRestaurant")
                                localStorage.saveDataString(
                                    context,
                                    it.nome_fantasia,
                                    "nameRestaurant"
                                )
                                localStorage.saveDataString(
                                    context,
                                    it.nome_categoria_restaurante,
                                    "nameCategoryRestaurant"
                                )

                                var openProductsRestaurant =
                                    Intent(context, ProductsRestaurantScreen::class.java)
                                openProductsRestaurant.putExtra("name_restaurant", it.nome_fantasia)
                                context.startActivity(openProductsRestaurant)

                            },
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
                                        .size(50.dp)
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
                                        text = it.nome_categoria_restaurante,
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

}