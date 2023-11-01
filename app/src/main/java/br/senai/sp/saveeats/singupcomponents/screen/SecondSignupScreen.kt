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
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Numbers
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
fun SecondSignup(navController: NavController, localStorage: Storage) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var cep by rememberSaveable { mutableStateOf("") }
    var state by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var neighborhood by rememberSaveable { mutableStateOf("") }
    var street by rememberSaveable { mutableStateOf("") }
    var number by rememberSaveable { mutableStateOf("") }

    var validateCep by rememberSaveable { mutableStateOf(true) }
    var validateState by rememberSaveable { mutableStateOf(true) }
    var validateCity by rememberSaveable { mutableStateOf(true) }
    var validateNeighborhood by rememberSaveable { mutableStateOf(true) }
    var validateStreet by rememberSaveable { mutableStateOf(true) }
    var validateNumber by rememberSaveable { mutableStateOf(true) }

    val validateCepError = stringResource(id = R.string.cep_error)
    val validateStateError = stringResource(id = R.string.state_error)
    val validateCityError = stringResource(id = R.string.city_error)
    val validateNeighborhoodError = stringResource(id = R.string.neighborhood_error)
    val validateStreetError = stringResource(id = R.string.street_error)
    val validateNumberError = stringResource(id = R.string.number_error)

    fun validateData(
        cep: String,
        state: String,
        city: String,
        neighborhood: String,
        street: String,
        number: String
    ): Boolean {

        validateCep = cep.isNotBlank()
        validateState = state.isNotBlank()
        validateCity = city.isNotBlank()
        validateNeighborhood = neighborhood.isNotBlank()
        validateStreet = street.isNotBlank()
        validateNumber = number.isNotBlank()

        return validateCep && validateState && validateCity && validateNeighborhood && validateStreet && validateNumber

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
                            .padding(top = 40.dp)
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

                        InputOutlineTextField(
                            value = state,
                            onValueChange = { state = it },
                            label = stringResource(id = R.string.state),
                            showError = !validateState,
                            errorMessage = validateStateError,
                            leadingIconImageVector = Icons.Default.LocationCity,
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
                            value = city,
                            onValueChange = { city = it },
                            label = stringResource(id = R.string.city),
                            showError = !validateCity,
                            errorMessage = validateCityError,
                            leadingIconImageVector = Icons.Default.LocationCity,
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
                            value = neighborhood,
                            onValueChange = { neighborhood = it },
                            label = stringResource(id = R.string.neighborhood),
                            showError = !validateNeighborhood,
                            errorMessage = validateNeighborhoodError,
                            leadingIconImageVector = Icons.Default.LocationCity,
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
                            value = street,
                            onValueChange = { street = it },
                            label = stringResource(id = R.string.street),
                            showError = !validateStreet,
                            errorMessage = validateStreetError,
                            leadingIconImageVector = Icons.Default.LocationCity,
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
                            value = number,
                            onValueChange = { number = it },
                            label = stringResource(id = R.string.number),
                            showError = !validateNumber,
                            errorMessage = validateNumberError,
                            leadingIconImageVector = Icons.Default.Numbers,
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

                                    if (validateData(
                                            cep,
                                            state,
                                            city,
                                            neighborhood,
                                            street,
                                            number
                                        )
                                    ) {

                                        localStorage.saveDataString(context, cep, "cep")
                                        localStorage.saveDataString(context, state, "state")
                                        localStorage.saveDataString(context, city, "city")
                                        localStorage.saveDataString(
                                            context,
                                            neighborhood,
                                            "neighborhood"
                                        )
                                        localStorage.saveDataString(context, street, "street")
                                        localStorage.saveDataString(context, number, "number")

                                        navController.navigate("third_signup_screen")

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