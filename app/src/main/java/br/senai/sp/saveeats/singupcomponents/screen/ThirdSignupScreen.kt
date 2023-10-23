package br.senai.sp.saveeats.singupcomponents.screen

import android.util.Log
import android.util.Patterns
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.ShapeDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.saveeats.MainActivity
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.CustomButton
import br.senai.sp.saveeats.components.InputOutlineTextField
import br.senai.sp.saveeats.model.SignupRepository
import kotlinx.coroutines.launch

@Composable
fun ThirdSignupScreen(
    navController: NavController,
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope
) {

    var context = LocalContext.current
    var focusManager = LocalFocusManager.current

    var name = localStorage.readDataString(context, "name")
    var cpf = localStorage.readDataString(context, "cpf")
    var phone = localStorage.readDataString(context, "phone")
    var cep = localStorage.readDataString(context, "cep")
    var state = localStorage.readDataString(context, "state")
    var city = localStorage.readDataString(context, "city")
    var neighborhood = localStorage.readDataString(context, "neighborhood")
    var street = localStorage.readDataString(context, "street")
    var number = localStorage.readDataString(context, "number")
    var emailGoogle = localStorage.readDataString(context, "email")
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }


    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var validateConfirmPassword by rememberSaveable { mutableStateOf(true) }
    var validatearePasswordsEqual by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisibible by rememberSaveable { mutableStateOf(false) }
    var isConfirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val validateEmailError = stringResource(id = R.string.email_error)
    val validatePasswordError = stringResource(id = R.string.password_error)
    val validateEqualPasswordError = stringResource(id = R.string.password_equal_error)

    fun validateData(
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {

        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%ˆ&+-]).{8,}".toRegex()

        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePassword = passwordRegex.matches(password)
        validateConfirmPassword = passwordRegex.matches(confirmPassword)
        validatearePasswordsEqual = password == confirmPassword

        return validateEmail && validatePassword && validateConfirmPassword && validatearePasswordsEqual

    }

    fun signup(
        name: String,
        cpf: String,
        cep: String,
        state: String,
        city: String,
        neighborhood: String,
        street: String,
        number: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ) {

        if (validateData(
                email,
                password,
                confirmPassword
            )
        ) {

            val signup = SignupRepository()

            lifecycleScope.launch {

                val response = signup.signupClient(
                    name,
                    cpf,
                    cep,
                    state,
                    city,
                    neighborhood,
                    street,
                    number,
                    email,
                    phone,
                    password
                )

                if (response.isSuccessful) {

                    Toast.makeText(context, "Faça login", Toast.LENGTH_SHORT).show()
                    navController.navigate("login_screen")

                } else {

                    val errorBody = response.errorBody()?.string()

                    Log.e(MainActivity::class.java.simpleName, "Erro durante o login: $errorBody")
                    Toast.makeText(
                        context, "Esse e-mail já está vinculado a uma conta", Toast.LENGTH_SHORT
                    ).show()

                }

            }

        } else {
            Toast.makeText(context, "Please, review fields", Toast.LENGTH_SHORT).show()
        }

    }

    androidx.compose.material3.Surface(
        modifier = Modifier.fillMaxSize()
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
                        contentDescription = "Prato de comida"
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
                        contentDescription = "Hamburguer"
                    )

                    Image(
                        modifier = Modifier
                            .size(280.dp)
                            .offset(x = 160.dp, y = -(180).dp),
                        painter = painterResource(id = R.drawable.pao),
                        contentDescription = "Pão"
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)
                    ) {

                        InputOutlineTextField(
                            value = if (emailGoogle == null) {
                                email
                            } else {
                                emailGoogle!!
                            },
                            onValueChange = { email = it },
                            label = stringResource(id = R.string.email),
                            showError = !validateEmail,
                            errorMessage = validateEmailError,
                            leadingIconImageVector = Icons.Default.AlternateEmail,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
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
                            isPasswordVisible = isPasswordVisibible,
                            onVisibilityChange = { isPasswordVisibible = it },
                            leadingIconImageVector = Icons.Default.Password,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }),
                            borderColor = Color(72, 138, 39),
                            border = ShapeDefaults.Small
                        )

                        InputOutlineTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = stringResource(id = R.string.confirm_password),
                            showError = !validateConfirmPassword || !validatearePasswordsEqual,
                            errorMessage = if (!validateConfirmPassword) validatePasswordError else validateEqualPasswordError,
                            isPasswordField = true,
                            isPasswordVisible = isConfirmPasswordVisible,
                            onVisibilityChange = { isConfirmPasswordVisible = it },
                            leadingIconImageVector = Icons.Default.Password,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(onDone = {
                                focusManager.clearFocus()
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

                                    if (emailGoogle == null) {
                                        signup(
                                            name!!,
                                            cpf!!,
                                            cep!!,
                                            state!!,
                                            city!!,
                                            neighborhood!!,
                                            street!!,
                                            number!!,
                                            email,
                                            phone!!,
                                            password,
                                            confirmPassword
                                        )
                                    } else {
                                        signup(
                                            name!!,
                                            cpf!!,
                                            cep!!,
                                            state!!,
                                            city!!,
                                            neighborhood!!,
                                            street!!,
                                            number!!,
                                            emailGoogle!!,
                                            phone!!,
                                            password,
                                            confirmPassword
                                        )

                                    }
                                }, text = stringResource(id = R.string.signup)
                            )

                        }

                    }

                }

            }

        }

    }

}