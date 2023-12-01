package br.senai.sp.saveeats.recipesandtips.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.CategoryRecipes
import br.senai.sp.saveeats.model.CategoryRecipesList
import br.senai.sp.saveeats.model.CategoryTips
import br.senai.sp.saveeats.model.CategoryTipsList
import br.senai.sp.saveeats.model.Recipes
import br.senai.sp.saveeats.model.RecipesList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.model.Tips
import br.senai.sp.saveeats.model.TipsList
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RecipesTipsScreen(navController: NavController, localStorage: Storage) {

    val context = LocalContext.current

    var progressState by remember {
        mutableStateOf(true)
    }

    var searchState by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var listRecipes by remember {
        mutableStateOf(listOf<Recipes>())
    }

    var listTips by remember {
        mutableStateOf(listOf<Tips>())
    }

    var listCategoryRecipes by remember {
        mutableStateOf(listOf<CategoryRecipes>())
    }

    var listCategoryTips by remember {
        mutableStateOf(listOf<CategoryTips>())
    }

    val callRecipes = RetrofitFactory
        .getRecipes()
        .getRecipes()

    callRecipes.enqueue(object : Callback<RecipesList> {

        override fun onResponse(
            call: Call<RecipesList>,
            response: Response<RecipesList>
        ) {
            listRecipes = response.body()!!.receitas
        }

        override fun onFailure(
            call: Call<RecipesList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

        }

    })

    val callCategoryRecipes = RetrofitFactory
        .getCategoryRecipes()
        .getCategoryRecipes()

    callCategoryRecipes.enqueue(object : Callback<CategoryRecipesList> {

        override fun onResponse(
            call: Call<CategoryRecipesList>,
            response: Response<CategoryRecipesList>
        ) {
            listCategoryRecipes = response.body()!!.categorias
        }

        override fun onFailure(
            call: Call<CategoryRecipesList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

        }

    })

    val callTips = RetrofitFactory
        .getTips()
        .getTips()

    callTips.enqueue(object : Callback<TipsList> {

        override fun onResponse(
            call: Call<TipsList>,
            response: Response<TipsList>
        ) {
            listTips = response.body()!!.dica
        }

        override fun onFailure(
            call: Call<TipsList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

        }

    })

    val callCategoryTips = RetrofitFactory
        .getCategoryTips()
        .getCategoryTips()

    callCategoryTips.enqueue(object : Callback<CategoryTipsList> {

        override fun onResponse(
            call: Call<CategoryTipsList>,
            response: Response<CategoryTipsList>
        ) {
            listCategoryTips = response.body()!!.categorias
        }

        override fun onFailure(
            call: Call<CategoryTipsList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

        }

    })

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.recipes_and_tips),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W300,
                    color = Color(76, 77, 76)
                )

            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    modifier = Modifier
                        .padding(start = 55.dp)
                        .clickable { progressState = true },
                    text = stringResource(id = R.string.recipes),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400)
                )

                Spacer(modifier = Modifier.width(170.dp))

                Text(
                    modifier = Modifier
                        .padding(end = 50.dp)
                        .clickable { progressState = false },
                    text = stringResource(id = R.string.tips),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400)
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(
                        if (!progressState) {
                            Color(41, 95, 27)
                        } else {
                            Color(191, 193, 198, 255)
                        },
                        shape = RoundedCornerShape(size = 5.dp)
                    )

            ) {

                Row(
                    modifier = Modifier
                        .width(190.dp)
                        .height(2.dp)
                        .background(
                            color = if (progressState) {
                                Color(41, 95, 27)
                            } else {
                                Color(191, 193, 198, 255)
                            },
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                ) {}

            }

            Spacer(modifier = Modifier.height(25.dp))

            if (progressState) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        modifier = Modifier
                            .width(360.dp)
                            .height(45.dp),
                        value = searchState,
                        onValueChange = {
                            searchState = it
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.Gray
                            )
                        },
                        label = {
                            Text(
                                text = "Search",
                            )
                        },
                        shape = RoundedCornerShape(100)

                    )

                }

                Spacer(modifier = Modifier.height(25.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    items(listCategoryRecipes) {

                        Surface(
                            modifier = Modifier
                                .width(120.dp)
                                .height(40.dp)
                                .padding(end = 20.dp),
                            shape = RoundedCornerShape(30.dp),
                            border = BorderStroke(0.8.dp, Color(212, 212, 212))
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Text(text = it.categoria)

                            }

                        }

                    }

                }

                Spacer(modifier = Modifier.height(25.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    items(listRecipes) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Surface(
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(160.dp)
                                    .padding(20.dp)
                                    .clickable {
                                        localStorage.saveDataInt(context, it.id_receita, "idRecipe")
                                        localStorage.saveDataString(
                                            context,
                                            it.foto_receita,
                                            "imageRecipe"
                                        )
                                        localStorage.saveDataString(
                                            context,
                                            it.nome_receita,
                                            "nameRecipe"
                                        )
                                        localStorage.saveDataString(
                                            context,
                                            it.descricao,
                                            "descriptionRecipe"
                                        )
                                        localStorage.saveDataInt(
                                            context,
                                            it.numero_porcoes,
                                            "portionRecipe"
                                        )
                                        localStorage.saveDataString(
                                            context,
                                            it.tempo_preparo,
                                            "timeRecipe"
                                        )
                                        localStorage.saveDataString(
                                            context,
                                            it.nivel_dificuldade,
                                            "levelRecipe"
                                        )
                                        localStorage.saveDataString(
                                            context,
                                            it.modo_preparo,
                                            "methodOfPreparationRecipe"
                                        )
                                        navController.navigate("recipe_screen")
                                    },
                                shape = RoundedCornerShape(20.dp),
                                elevation = 10.dp
                            ) {

                                AsyncImage(
                                    model = it.foto_receita,
                                    contentDescription = "Image Recipes"
                                )

                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {

                                Text(
                                    text = it.nome_receita,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W500
                                )

                                Text(
                                    text = "Porção: ${it.numero_porcoes}",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.W400
                                )

                                Text(
                                    text = "Tempo de preparo: ${it.tempo_preparo}",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W400
                                )

                            }

                        }

                    }

                }

            } else {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        modifier = Modifier
                            .width(360.dp)
                            .height(45.dp),
                        value = searchState,
                        onValueChange = {
                            searchState = it
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.Gray
                            )
                        },
                        label = {
                            Text(text = stringResource(id = R.string.search))
                        },
                        shape = RoundedCornerShape(100)

                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        items(listCategoryTips) {

                            Surface(
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(45.dp)
                                    .padding(end = 20.dp),
                                shape = RoundedCornerShape(30.dp),
                                border = BorderStroke(0.8.dp, Color(212, 212, 212))
                            ) {

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Text(
                                        text = it.categoria,
                                        color = Color(20, 58, 11),
                                        textAlign = TextAlign.Center
                                    )

                                }

                            }

                        }

                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        items(listTips) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Surface(
                                    modifier = Modifier
                                        .width(160.dp)
                                        .height(160.dp)
                                        .padding(20.dp)
                                        .clickable {
                                            localStorage.saveDataString(
                                                context,
                                                it.foto_da_receita,
                                                "imageTip"
                                            )
                                            localStorage.saveDataString(
                                                context,
                                                it.nome_da_receita,
                                                "nameTip"
                                            )
                                            localStorage.saveDataString(
                                                context,
                                                it.descricao_da_receita,
                                                "descriptionTip"
                                            )
                                            localStorage.saveDataString(
                                                context,
                                                it.categoria,
                                                "categoryTip"
                                            )
                                            navController.navigate("tip_screen")
                                        },
                                    shape = RoundedCornerShape(20.dp),
                                    elevation = 10.dp
                                ) {

                                    AsyncImage(
                                        model = it.foto_da_receita,
                                        contentDescription = "Image Tips"
                                    )

                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {

                                    localStorage.saveDataInt(context, it.id_categoria, "idRecipe")

                                    Text(
                                        text = it.nome_da_receita,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.W500
                                    )

                                    Text(
                                        text = it.categoria,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.W400
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