package br.senai.sp.saveeats.singup.screen

import android.net.Uri
import android.util.Patterns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.rememberAsyncImagePainter

@Composable
fun FirstSignup(navController: NavController, localStorage: Storage) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var name by rememberSaveable { mutableStateOf("") }
    var cpf by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var imageUri by rememberSaveable { mutableStateOf("") }

    var validateName by rememberSaveable { mutableStateOf(true) }
    var validateCpf by rememberSaveable { mutableStateOf(true) }
    var validatePhone by rememberSaveable { mutableStateOf(true) }

    val validateNameError = stringResource(id = R.string.name_error)
    val validateCpfError = stringResource(id = R.string.cpf_error)
    val validatePhoneError = stringResource(id = R.string.phone_error)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri = uri.toString() }
    }

    val painter = rememberAsyncImagePainter(
        imageUri.ifEmpty {
            R.drawable.perfil
        }
    )

    fun validateData(
        name: String, cpf: String, phone: String
    ): Boolean {

        validateName = name.isNotBlank()
        validateCpf = cpf.isNotBlank()
        validatePhone = Patterns.PHONE.matcher(phone).matches()

        return validateName && validateCpf && validatePhone

    }

    Surface (
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
                    text = stringResource(id = R.string.singup).uppercase(),
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
                            .verticalScroll(scrollState),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Surface(
                            color = Color(255,255,255)
                        ) {
                            Image(
                                painter = painter,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(150.dp)
                                    .background(Color.White)
//                                    .border(
//                                        width = 1.dp,
//                                        color = Color(72, 138, 39),
//                                        shape = CircleShape
//                                    )
                            )
                            Box(
                                modifier = Modifier
                                    .padding(
                                        top = 100.dp,
                                        start = 100.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color(72, 138, 39),
                                        shape = CircleShape
                                    )
                                    .clickable { launcher.launch("image/*") }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(Color.White)
                                        .size(40.dp)
                                        .padding(5.dp)


                                )
                            }
                        }

                        InputOutlineTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = stringResource(id = R.string.name),
                            showError = !validateName,
                            errorMessage = validateNameError,
                            leadingIconImageVector = Icons.Default.Person,
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
                            value = cpf,
                            onValueChange = { cpf = it },
                            label = stringResource(id = R.string.cpf),
                            showError = !validateCpf,
                            errorMessage = validateCpfError,
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

                        InputOutlineTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            label = stringResource(id = R.string.phone),
                            showError = !validatePhone,
                            errorMessage = validatePhoneError,
                            leadingIconImageVector = Icons.Default.Phone,
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
                                            name,
                                            cpf,
                                            phone
                                        )
                                    ) {

                                        localStorage.saveDataString(context, name, "name")
                                        localStorage.saveDataString(context, cpf, "cpf")
                                        localStorage.saveDataString(context, phone, "phone")
                                        localStorage.saveDataString(context, imageUri,"photoClient")

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