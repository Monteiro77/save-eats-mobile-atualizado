package br.senai.sp.saveeats.ordercomponents

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.HistoricoCliente
import br.senai.sp.saveeats.model.Order
import br.senai.sp.saveeats.model.OrderInformation
import br.senai.sp.saveeats.model.ProductOrderList
import br.senai.sp.saveeats.model.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

@Composable
fun DetailsOrderScreen(
    navController: NavController,
    localStorage: Storage
) {

    val context = LocalContext.current


    var productsOrder by remember {
        mutableStateOf(
            listOf<ProductOrderList>()
        )
    }

    val idOrder = localStorage.readDataInt(context, "idOrder")

    val callPedido = RetrofitFactory
        .getOrderById()
        .getOrderById(112)


    var status by remember {
        mutableStateOf(false)
    }
    callPedido.enqueue(object : retrofit2.Callback<Order> {
        override fun onResponse(
            call: Call<Order>,
            response: Response<Order>
        ) {
            productsOrder = response.body()!!.detalhes_do_pedido.produtos
            Log.e("Teste1234567", "${productsOrder}")
            status = true
        }

        override fun onFailure(
            call: Call<Order>,
            t: Throwable
        ) {
            Log.e("TESTE2", "onFailure: ${t.message}")
        }

    })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        navController.popBackStack()
                    }
                    .scale(scaleX = -1f, scaleY = 1f),
                tint = Color(20, 58, 11)

            )

            Spacer(modifier = Modifier.width(50.dp))

            Text(
                text = stringResource(id = R.string.details_orders),
                fontSize = 25.sp
            )

        }

        


    }

}
