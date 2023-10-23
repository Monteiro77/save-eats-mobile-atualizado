package br.senai.sp.saveeats.productcomponents.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.components.CustomButton
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import coil.compose.AsyncImage

class ProductScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var getImageProduct = intent.getStringExtra("imageProduct")

        var getNameProduct = intent.getStringExtra("nameProduct")

        var getDescriptionProduct = intent.getStringExtra("descriptionProduct")

        var getPriceProduct = intent.getFloatExtra("priceProduct", 0.0F)

        Log.e("TESTE", "onCreate: $getPriceProduct")

        setContent {
            SaveEatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ProductScreen(
                        getImageProduct.toString(),
                        getNameProduct.toString(),
                        getDescriptionProduct.toString(),
                        getPriceProduct.toString()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    imageProduct: String,
    nameProduct: String,
    descriptionProduct: String,
    priceProduct: String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                shape = RoundedCornerShape(bottomStart = 35.dp, bottomEnd = 35.dp)
            ) {

                AsyncImage(
                    model = imageProduct,
                    contentDescription = "Image Product",
                    contentScale = ContentScale.FillBounds
                )

            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 20.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    Text(
                        text = nameProduct,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W400,
                        letterSpacing = 2.sp
                    )

                    Text(
                        text = descriptionProduct,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W300
                    )

                    Text(
                        text = "A partir de R$ $priceProduct",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        letterSpacing = 2.sp,
                        color = Color(20, 58, 11)
                    )

                }

                Spacer(modifier = Modifier.height(40.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    Text(
                        text = stringResource(id = R.string.any_observations),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .width(370.dp)
                            .height(150.dp),
                        value = "",
                        onValueChange = {}
                    )


                }

            }

            Spacer(modifier = Modifier.height(40.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                androidx.compose.material.Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(255.dp)
                        .height(55.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(76, 132, 62)
                    )

                )

                {

                    Text(
                        text = stringResource(id = R.string.add_to_cart).uppercase(),
                        fontSize = 16.sp,
                        color = Color.White
                    )

                }

            }

        }

    }

}