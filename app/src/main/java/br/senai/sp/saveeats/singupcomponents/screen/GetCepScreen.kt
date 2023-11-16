package br.senai.sp.saveeats.singupcomponents.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.CustomButton
import br.senai.sp.saveeats.components.InputOutlineTextField

@Composable
fun GetCepScreen(
    navController: NavController,
    localStorage: Storage
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var validateCep by rememberSaveable { mutableStateOf(true) }

    val validateCepError = stringResource(id = R.string.cep_error)

    var cep by rememberSaveable {
        mutableStateOf("")
    }

    fun validateCep(
        cep: String
    ): Boolean {

        validateCep = cep.isNotBlank()

        return validateCep


    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp), Alignment.BottomCenter
                ) {

                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .offset(x = -(185).dp, y = -(155).dp),
                        painter = painterResource(id = R.drawable.prato),
                        contentDescription = "Plate of Food"
                    )

                    Image(
                        modifier = Modifier.size(200.dp),
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo"
                    )

                }

            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(id = R.string.signup).uppercase(),
                    color = Color(41, 95, 27),
                    fontWeight = FontWeight(700),
                    fontSize = 36.sp
                )

                Text(
                    text = stringResource(id = R.string.create_your_account),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400)
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier.fillMaxWidth(), Alignment.Center
                ) {

                    Image(
                        modifier = Modifier
                            .size(250.dp)
                            .offset(x = -(200).dp, y = 40.dp),
                        painter = painterResource(id = R.drawable.hamburguer),
                        contentDescription = "Hamburger"
                    )

                    Image(
                        modifier = Modifier
                            .size(280.dp)
                            .offset(x = 160.dp, y = -(180).dp),
                        painter = painterResource(id = R.drawable.pao),
                        contentDescription = "Bread"
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(scrollState)
                            .padding(top = 100.dp)
                    ) {

                        InputOutlineTextField(
                            value = cep,
                            onValueChange = { cep = it },
                            label = stringResource(id = R.string.cep),
                            showError = !validateCep,
                            errorMessage = validateCepError,
                            leadingIconImageVector = Icons.Default.LocationOn,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }),
                            borderColor = Color(72, 138, 39),
                            border = ShapeDefaults.Small
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            CustomButton(
                                onClick = {

                                    if (validateCep(
                                            cep
                                        )
                                    ) {

                                        localStorage.saveDataString(context, cep, "cep")

                                        navController.navigate("second_signup_screen")

                                    } else {

                                        Toast.makeText(
                                            context,
                                            "Please, review fields",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }
                                },
                                text = stringResource(id = R.string.next)
                            )

                        }
                    }
                }
            }
        }
    }

}

