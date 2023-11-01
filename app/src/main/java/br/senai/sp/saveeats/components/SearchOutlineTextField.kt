package br.senai.sp.saveeats.components

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.LifecycleCoroutineScope
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.model.RestaurantRepository
import br.senai.sp.saveeats.viewmodel.RestaurantViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchOutlineTextField(
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: RestaurantViewModel
) {

    val context = LocalContext.current

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val restaurants = remember { mutableStateListOf<String>() }

    var restaurant by remember {
        mutableStateOf("")
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val heightTextFields by remember {
        mutableStateOf(55.dp)
    }

    fun loadingRestaurant(
        viewModel: RestaurantViewModel
    ) {

        val restaurantRepository = RestaurantRepository()

        lifecycleScope.launch {

            val response = restaurantRepository.getRestaurant()

            Log.e("TAG:DS3T", "loadingRestaurant: ${response.body()}")

            if (response.isSuccessful) {

                val estadosResponse = response.body()?.restaurantes

                estadosResponse?.forEach { restaurant ->
                    restaurants.add(restaurant.nome_fantasia)
                    viewModel.selectRestaurant = restaurant.nome_fantasia
                }

            } else {

                Toast.makeText(context, "Erro durante carregar os estados", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }

    LaunchedEffect(key1 = true) {
        loadingRestaurant(viewModel)
    }

    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    isExpanded = false
                }
            )

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth()) {

                androidx.compose.material.TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightTextFields)
                        .border(
                            width = 0.8.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    value = restaurant,
                    onValueChange = {
                        restaurant = it
                        isExpanded = true
                    },

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),

                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),

                    label = {
                        Text(
                            text = stringResource(id = R.string.search)
                        )
                    },

                    singleLine = true,

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = colorResource(id = R.color.green_save_eats_light)
                        )
                    },

                    trailingIcon = {

                        IconButton(onClick = { isExpanded = !isExpanded }) {

                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = Color.Black
                            )

                        }

                    }

                )

            }

            AnimatedVisibility(visible = isExpanded) {

                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    elevation = 15.dp

                ) {

                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = 150.dp),
                    ) {

                        if (restaurant.isNotEmpty()) {
                            items(
                                restaurants.filter {
                                    it.lowercase()
                                        .contains(restaurant.lowercase()) || it.lowercase()
                                        .contains("others")
                                }
                                    .sorted()
                            ) {

                                CategoryItemsRestaurant(title = it) { title ->
                                    restaurant = title
                                    viewModel.selectRestaurant = title
                                    isExpanded = false
                                }

                            }
                        } else {
                            items(
                                restaurants.sorted()
                            ) {

                                CategoryItemsRestaurant(title = it) { title ->
                                    restaurant = title
                                    viewModel.selectRestaurant = title
                                    isExpanded = false
                                }

                            }

                        }

                    }

                }

            }

        }

    }

}

@Composable
fun CategoryItemsRestaurant(
    title: String,
    onSelect: (String) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp
        )
    }

}