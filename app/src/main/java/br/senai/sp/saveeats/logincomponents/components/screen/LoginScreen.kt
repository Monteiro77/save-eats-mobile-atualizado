package br.senai.sp.saveeats.logincomponents.screen

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Password
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
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.CustomButton
import br.senai.sp.saveeats.components.InputOutlineTextField
import br.senai.sp.saveeats.model.LoginRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
@Composable
fun LoginScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    localStorage: Storage
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisibility by rememberSaveable { mutableStateOf(false) }

    val validateEmailError = "The format of the email doesn't seem right"
    val validatePasswordError =
        "Must mix capital and non-capital letters, a number, special character and a minimum length of 8"

    fun validateData(email: String, password: String): Boolean {

        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%ˆ&+-]).{8,}".toRegex()

        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePassword = passwordRegex.matches(password)

        return validateEmail && validatePassword

    }

    fun login(
        email: String,
        password: String
    ) {

        if (validateData(email, password)) {

            val login = LoginRepository()

            lifecycleScope.launch {

                val response = login.loginClient(email, password)

                if (response.isSuccessful) {

                    val json = response.body().toString()

                    val jsonObject = JSONObject(json)

                    val clientObject = jsonObject.getJSONObject("clientes")

                    val id = clientObject.getInt("id")

                    val cpf = clientObject.getString("cpf")

                    val name = clientObject.getString("nome")

                    Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
                    localStorage.saveDataInt(context, id, "idClient")
                    localStorage.saveDataString(context, cpf, "cpfClient")
                    localStorage.saveDataString(context, name, "nameClient")
                    navController.navigate("home_screen")

                } else {

                    Toast.makeText(context, "E-mail or password invalid", Toast.LENGTH_SHORT).show()

                }

            }

        } else {
            Toast.makeText(context, "Please, review fields", Toast.LENGTH_SHORT).show()
        }

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
                        contentDescription = "Food Plate"
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
                    text = stringResource(id = R.string.welcome),
                    color = Color(41, 95, 27),
                    fontWeight = FontWeight(700),
                    fontSize = 36.sp
                )

                Text(
                    text = stringResource(id = R.string.enter_account),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400)
                )

            }

            Column(
                modifier = Modifier.fillMaxWidth()
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
                        contentDescription = "Pão"
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        InputOutlineTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = stringResource(id = R.string.email),
                            showError = !validateEmail,
                            errorMessage = validateEmailError,
                            leadingIconImageVector = Icons.Default.AlternateEmail,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }),
                            borderColor = Color(72, 138, 39),
                            border = ShapeDefaults.Small
                        )

                        InputOutlineTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = stringResource(id = R.string.password),
                            showError = !validatePassword,
                            errorMessage = validatePasswordError,
                            isPasswordField = true,
                            isPasswordVisible = isPasswordVisibility,
                            onVisibilityChange = { isPasswordVisibility = it },
                            leadingIconImageVector = Icons.Default.Password,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(onDone = {
                                focusManager.clearFocus()
                            }),
                            borderColor = Color(72, 138, 39),
                            border = ShapeDefaults.Small
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 35.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = stringResource(id = R.string.forget_password),
                                fontWeight = FontWeight.Medium,
                                color = Color(20, 58, 11)
                            )
                        }

                    }

                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CustomButton(
                    onClick = {
                        login(email, password)
                    },
                    text = stringResource(id = R.string.login)
                )

            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = stringResource(id = R.string.dont_have_an_account),
                    fontSize = 14.sp,
                    color = Color(20, 58, 11)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    modifier = Modifier
                        .clickable {
                            navController.navigate("first_signup_screen")
                        },
                    text = stringResource(id = R.string.create_your_account),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(20, 58, 11)
                )

            }

        }

    }

}