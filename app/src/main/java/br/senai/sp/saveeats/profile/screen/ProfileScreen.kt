package br.senai.sp.saveeats.profile.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(navController: NavController, localStorage: Storage) {

    val context = LocalContext.current

    val photo = localStorage.readDataString(context, "photoClient")

    val websiteUrl = "https://lion-school-frontend.vercel.app/"

    val nameClient = localStorage.readDataString(context, "nameClient")

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
                    .padding(start = 20.dp, top = 35.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(shape = CircleShape),
                    model = photo,
                    contentScale = ContentScale.FillHeight,
                    contentDescription = "Image Client"
                )

                Text(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    text = nameClient!!,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )


            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .clickable {
                            navController.navigate("edit_profile_screen")
                        },
                    color = colorResource(id = R.color.white)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(start = 18.dp, top = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier
                                .size(28.dp),
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile"
                        )

                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {

                            Text(
                                text = stringResource(id = R.string.my_data),
                                fontSize = 15.sp
                            )

                            Text(
                                text = stringResource(id = R.string.my_account_information),
                                fontSize = 14.sp,
                                color = Color(123, 125, 123)
                            )

                        }

                        Icon(
                            modifier = Modifier
                                .size(18.dp)
                                .offset(x = 130.dp),
                            imageVector = Icons.Default.KeyboardArrowRight,
                            tint = Color(123, 125, 123),
                            contentDescription = "Arrow Right"
                        )

                    }

                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(.2.dp)
                        .padding(start = 15.dp),
                    color = Color.Black
                )

            }

            Spacer(modifier = Modifier.height(450.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .clickable {
                            val uri = Uri.parse(websiteUrl)
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            context.startActivity(intent)
                        },
                    color = colorResource(id = R.color.white)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(start = 18.dp, top = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Help,
                            contentDescription = "Help",
                            tint = Color(123, 125, 123)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = stringResource(id = R.string.help),
                            fontSize = 15.sp,
                            color = Color(123, 125, 123)
                        )

                        Spacer(modifier = Modifier.width(280.dp))

                        Icon(
                            modifier = Modifier
                                .size(18.dp),
                            imageVector = Icons.Default.KeyboardArrowRight,
                            tint = Color(123, 125, 123),
                            contentDescription = "Arrow Right"
                        )

                    }

                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(.2.dp)
                        .padding(start = 15.dp),
                    color = Color.Black
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .clickable {
                            navController.navigate("login_screen")
                        },
                    color = colorResource(id = R.color.white)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(start = 18.dp, top = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout",
                            tint = Color(123, 125, 123)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = stringResource(id = R.string.logout),
                            fontSize = 15.sp,
                            color = Color(123, 125, 123)
                        )

                        Spacer(modifier = Modifier.width(292.dp))

                        Icon(
                            modifier = Modifier
                                .size(18.dp),
                            imageVector = Icons.Default.KeyboardArrowRight,
                            tint = Color(123, 125, 123),
                            contentDescription = "Arrow Right"
                        )

                    }

                }

            }

        }

    }

}