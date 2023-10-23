package br.senai.sp.saveeats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import coil.compose.AsyncImage

class TipsScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val getImageTips = intent.getStringExtra("imageTips")

        val getNameTips = intent.getStringExtra("nameTips")

        val getDescriptionTips = intent.getStringExtra("descriptionTips")

        val getCategoryTips = intent.getStringExtra("categoryTips")


        super.onCreate(savedInstanceState)
        setContent {
            SaveEatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipScreen(
                        getImageTips.toString(),
                        getNameTips.toString(),
                        getDescriptionTips.toString(),
                        getCategoryTips.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun TipScreen(imageTips: String, nameTips: String, descriptionTips: String, categoryTips: String) {

    Column (
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

            Text(
                modifier = Modifier
                    .width(250.dp)
                    .offset(y = 20.dp),
                text = nameTips,
                fontSize = 22.sp,
                fontWeight = FontWeight.W500,
                color = Color(20, 58, 11),
                textAlign = TextAlign.Center
            )

            Divider(
                modifier = Modifier
                    .width(380.dp)
                    .height(2.dp)
                    .offset(y = 30.dp),
                color = Color(200,200,200)
            )

        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface (
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
            ) {

                AsyncImage(
                    model = imageTips,
                    contentDescription = "Image Tips"
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = descriptionTips
            )

        }

    }

}