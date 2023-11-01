package br.senai.sp.saveeats.singupcomponents.screen

import android.util.Patterns
import android.view.SurfaceView
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
import br.senai.sp.saveeats.model.SignupRepository
import kotlinx.coroutines.launch

@Composable
fun ThirdSignupScreen(
    navController: NavController,
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val name = localStorage.readDataString(context, "name")
    val cpf = localStorage.readDataString(context, "cpf")
    val phone = localStorage.readDataString(context, "phone")
    val cep = localStorage.readDataString(context, "cep")
    val state = localStorage.readDataString(context, "state")
    val city = localStorage.readDataString(context, "city")
    val neighborhood = localStorage.readDataString(context, "neighborhood")
    val street = localStorage.readDataString(context, "street")
    val number = localStorage.readDataString(context, "number")
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var validateConfirmPassword by rememberSaveable { mutableStateOf(true) }
    var validatePasswordsEqual by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisibility by rememberSaveable { mutableStateOf(false) }
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
        validatePasswordsEqual = password == confirmPassword

        return validateEmail && validatePassword && validateConfirmPassword && validatePasswordsEqual

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

                    Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
                    navController.navigate("home_screen")

                } else {

                    Toast.makeText(
                        context, "This email is already linked to an account", Toast.LENGTH_SHORT
                    ).show()

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
                            .padding(top = 40.dp)
                    ) {

                        InputOutlineTextField(
                            value = email,
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
                            isPasswordVisible = isPasswordVisibility,
                            onVisibilityChange = { isPasswordVisibility = it },
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
                            showError = !validateConfirmPassword || !validatePasswordsEqual,
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

                                }, text = stringResource(id = R.string.signup)
                            )

                        }

                    }

                }

            }

        }

    }

}