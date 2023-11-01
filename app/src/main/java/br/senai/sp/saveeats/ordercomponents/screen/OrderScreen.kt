package br.senai.sp.saveeats.ordercomponents.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.CustomButton
import br.senai.sp.saveeats.model.FormPayment
import br.senai.sp.saveeats.model.FormPaymentList
import br.senai.sp.saveeats.model.OrderRepository
import br.senai.sp.saveeats.model.RetrofitFactory
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController,
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope,
    teste: String,
    onValueChange: (String) -> Unit
) {

    val context = LocalContext.current

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val streetClient = localStorage.readDataString(context, "streetClient")
    val numberAddressClient = localStorage.readDataInt(context, "numberAddressClient")
    val cityClient = localStorage.readDataString(context, "cityClient")

    val deliveryTime = localStorage.readDataString(context, "deliveryTime")
    val deliveryValue = localStorage.readDataFloat(context, "deliveryValue")
    val priceProduct = localStorage.readDataFloat(context, "priceProduct")

    val idClient = localStorage.readDataInt(context, "idClient")
    val idRestaurant = localStorage.readDataInt(context, "idRestaurant")
    val cpfClient = localStorage.readDataString(context, "cpfClient")

    val sumDeliveryProduct = deliveryValue + priceProduct

    var listFormPayment by remember {
        mutableStateOf<List<FormPayment>>(emptyList())
    }

    val callFormPayment = RetrofitFactory.getFormPayment().getFormPayment(1)

    callFormPayment.enqueue(object : Callback<FormPaymentList> {
        override fun onResponse(
            call: Call<FormPaymentList>, response: Response<FormPaymentList>
        ) {
            listFormPayment = response.body()!!.formas_de_pagamento_do_restaurante
        }

        override fun onFailure(
            call: Call<FormPaymentList>, t: Throwable
        ) {

            Log.i("ds3t", "onFailure: ${t.message}")

        }

    })

    var teste = teste

    fun order(
        idStatus: Int,
        idRestaurantFormPayment: Int,
        idRestaurantDeliveryArea: Int,
        idClient: Int,
        idRestaurant: Int,
        idProductOne: Int,
        idProductTwo: Int? = null
    ) {

        val order = OrderRepository()

        lifecycleScope.launch {

            val response = order.order(
                idStatus,
                idRestaurantFormPayment,
                idRestaurantDeliveryArea,
                idClient,
                idRestaurant,
                idProductOne,
                idProductTwo
            )

            if (response.isSuccessful) {
                Toast.makeText(context, "Teste", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Deu Erro", Toast.LENGTH_SHORT).show()
            }

        }

    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier.offset(x = -(90).dp)
                ) {

                    IconButton(onClick = { navController.popBackStack() }) {

                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Arrow Back",
                            tint = colorResource(id = R.color.green_save_eats_dark)
                        )

                    }

                }

                Text(
                    text = stringResource(id = R.string.shopping_cart),
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.green_save_eats_dark)
                )

            }

            Divider(
                modifier = Modifier
                    .width(320.dp)
                    .height(2.dp),
                colorResource(id = R.color.green_save_eats_dark)
            )

            Spacer(
                modifier = Modifier.height(35.dp)
            )

            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 45.dp)
                ) {

                    Text(
                        text = stringResource(id = R.string.delivery_address),
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green_save_eats_light)
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Row {

                        Text(
                            text = streetClient!!, fontSize = 19.sp, fontWeight = FontWeight.W500
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = numberAddressClient.toString(),
                            fontSize = 19.sp,
                            fontWeight = FontWeight.W500
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = cityClient!!, fontSize = 19.sp, fontWeight = FontWeight.W500
                        )

                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = stringResource(id = R.string.standard_delivery),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Today $deliveryTime", fontSize = 17.sp, fontWeight = FontWeight.W300
                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 45.dp)
                ) {

                    Text(
                        text = stringResource(id = R.string.summary_of_values),
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green_save_eats_light)
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 90.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = stringResource(id = R.string.subtotal),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W300
                        )

                        Text(
                            text = "R$$priceProduct", fontSize = 15.sp, fontWeight = FontWeight.W500
                        )

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 90.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = stringResource(id = R.string.delivery_fee),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W300
                        )

                        Text(
                            text = deliveryValue!!.toString(),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W500
                        )

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 90.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = stringResource(id = R.string.total),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W300
                        )

                        Text(
                            text = "R$$sumDeliveryProduct",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W500
                        )

                    }

                }

                Spacer(
                    modifier = Modifier.height(35.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 45.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 85.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = stringResource(id = R.string.payment_methods),
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.green_save_eats_light)
                        )

                        Row (
                            modifier = Modifier.fillMaxWidth()
                        ){

                            androidx.compose.material3.ExposedDropdownMenuBox(
                                expanded = isExpanded,
                                onExpandedChange = { isExpanded = it },
                            ) {

                                OutlinedTextField(
                                    modifier = Modifier
                                        .menuAnchor()
                                        .width(50.dp),
                                    value = teste,
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                                    }
                                )

                                ExposedDropdownMenu(
                                    expanded = isExpanded,
                                    onDismissRequest = { isExpanded = false }
                                ) {
                                    listFormPayment.forEach {
                                        androidx.compose.material3.DropdownMenuItem(
                                            text = {
                                                Text(
                                                    text = it.nome_forma_pagamento,
                                                    color = Color.Black
                                                )
                                            },
                                            onClick = {
                                                var test3 = it.nome_forma_pagamento
                                                teste = test3 // Atualiza a variável com a seleção do usuário
                                                onValueChange(it.nome_forma_pagamento) // Chama a função de retorno com o valor selecionado
                                                isExpanded = false
                                            }
                                        )
                                    }

                                }

                        }

                    }

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                            LazyRow(){
                                items(listFormPayment){
                                    if (teste == it.nome_forma_pagamento) {
                                        AsyncImage(
                                            modifier = Modifier
                                                .size(50.dp),
                                            model = it.foto_bandeira,
                                            contentDescription = ""
                                        )
                                    }
                                }
                            }

                        }

                    }

                }

                Spacer(
                    modifier = Modifier.height(35.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 45.dp)
                ) {

                    Text(
                        text = "CPF/CNPJ na nota",
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green_save_eats_light)
                    )

                    Text(text = cpfClient.toString())

                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CustomButton(
                        onClick = {
                            order(
                                6,
                                6,
                                idRestaurant,
                                idClient,
                                idRestaurant,
                                48,
                                50
                            )
                        },
                        text = stringResource(id = R.string.make_a_wish)
                    )

                }

            }

        }

    }

}