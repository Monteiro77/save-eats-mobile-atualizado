package br.senai.sp.saveeats.recipecomponents.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.CategoryTipsList
import br.senai.sp.saveeats.model.RecipeDetails
import br.senai.sp.saveeats.model.RecipeDetailsList
import br.senai.sp.saveeats.model.RetrofitFactory
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RecipeScreen(
    localStorage: Storage,
    lifecycleCoroutineScope: LifecycleCoroutineScope
) {

    val context = LocalContext.current

    val imageRecipe = localStorage.readDataString(context, "imageRecipe")
    val nameRecipe = localStorage.readDataString(context, "nameRecipe")
    val descriptionRecipe = localStorage.readDataString(context, "descriptionRecipe")
    val portionRecipe = localStorage.readDataInt(context, "portionRecipe")
    val timeRecipe = localStorage.readDataString(context, "timeRecipe")
    val levelRecipe = localStorage.readDataString(context, "levelRecipe")
    val methodOfPreparation = localStorage.readDataString(context, "methodOfPreparationRecipe")

    var progressState by remember {
        mutableStateOf(true)
    }

    var listRecipeDetails by remember {
        mutableStateOf(
            RecipeDetails()
        )
    }

    var status by remember {
        mutableStateOf(false)
    }

    val callCategoryTips = RetrofitFactory
        .getRecipeDetails()
        .getRecipeDetails(1)

    callCategoryTips.enqueue(object : Callback<RecipeDetailsList> {

        override fun onResponse(
            call: Call<RecipeDetailsList>,
            response: Response<RecipeDetailsList>
        ) {
            listRecipeDetails = response.body()!!.detalhes_receita
            status = true
        }

        override fun onFailure(
            call: Call<RecipeDetailsList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

        }

    })

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box {

                Icon(
                    modifier = Modifier
                        .offset(x = -(170.dp), y = 50.dp),
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Arrow Back",
                    tint = Color(20, 58, 11)
                )

            }

            if (status) {
                Text(
                    modifier = Modifier
                        .width(250.dp)
                        .offset(y = 20.dp),
                    text = listRecipeDetails.nome_receita!!,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(20, 58, 11),
                    textAlign = TextAlign.Center
                )
            } else {
                CircularProgressIndicator()
            }



        }

        Spacer(modifier = Modifier.height(55.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Row(
                    modifier = Modifier
                        .width(110.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "Icon Time"
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = timeRecipe!!,
                        fontWeight = FontWeight.W300
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .width(150.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .size(35.dp),
                        painter = painterResource(id = R.drawable.dish),
                        contentDescription = "Icon Dish"
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = "${portionRecipe} portions",
                        fontWeight = FontWeight.W300
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .width(110.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .size(35.dp),
                        painter = painterResource(id = R.drawable.chef),
                        contentDescription = "Icon Chef"
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = levelRecipe!!,
                        fontWeight = FontWeight.W300
                    )

                }

            }

            Column {

                Surface(
                    modifier = Modifier
                        .size(180.dp),
                    shape = CircleShape,
                    shadowElevation = 10.dp
                ) {

                    AsyncImage(
                        model = imageRecipe,
                        contentDescription = "Image Recipe",
                        contentScale = ContentScale.FillBounds
                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(25.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = Color(246, 246, 246),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .padding(start = 55.dp)
                            .clickable { progressState = true },
                        text = stringResource(id = R.string.description),
                        fontSize = 18.sp,
                        fontWeight = FontWeight(400)
                    )

                    Spacer(modifier = Modifier.width(40.dp))

                    Text(
                        modifier = Modifier
                            .clickable { progressState = false },
                        text = stringResource(id = R.string.method_of_preparation),
                        fontSize = 18.sp,
                        fontWeight = FontWeight(400)
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                if (progressState) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(x = 20.dp, y = 80.dp)
                    ) {

                        Text(
                            text = stringResource(id = R.string.description),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            color = Color(20, 58, 11)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            modifier = Modifier
                                .width(380.dp),
                            text = descriptionRecipe!!,
                            textAlign = TextAlign.Start
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = stringResource(id = R.string.ingredients),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            color = Color(20, 58, 11)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(text = listRecipeDetails.nome_receita!!)

                    }

                } else {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(x = 20.dp, y = 80.dp)
                    ) {

                        Text(
                            text = methodOfPreparation!!,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            color = Color(20, 58, 11)
                        )

                    }

                }

            }

        }

    }

}