package br.senai.sp.saveeats.editprofile.screen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.InputOutlineTextField
import br.senai.sp.saveeats.components.SecondCustomButton
import br.senai.sp.saveeats.editprofile.component.HeaderEditProfile
import br.senai.sp.saveeats.model.ClientAddress
import br.senai.sp.saveeats.model.ClientAddressList
import br.senai.sp.saveeats.model.EditProfileRepository
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.ui.theme.fontFamily
import coil.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun EditProfileScreen(
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController
) {

    var storageRef: StorageReference = FirebaseStorage.getInstance().reference.child("images")
    val firebase: FirebaseFirestore = FirebaseFirestore.getInstance()


    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val idClient = localStorage.readDataInt(context, "idClient")
    var imageUri by remember {
        mutableStateOf(
            localStorage.readDataString(context, "photoClient").toString()
        )
    }

    val verificationURI = localStorage.readDataString(context, "photoClient")
    var photoValue by remember { mutableStateOf("") }
    var nameClient by remember {
        mutableStateOf(
            localStorage.readDataString(context, "nameClient").toString()
        )
    }
    var cpfClient by remember {
        mutableStateOf(
            localStorage.readDataString(context, "cpfClient").toString()
        )
    }
    var passwordClient by remember {
        mutableStateOf(
            localStorage.readDataString(
                context,
                "passwordClient"
            ).toString()
        )
    }
    var numberClient by remember {
        mutableStateOf(
            localStorage.readDataString(
                context,
                "phoneClient"
            ).toString()
        )
    }
    var emailClient by remember {
        mutableStateOf(
            localStorage.readDataString(context, "emailClient").toString()
        )
    }
    var isPasswordVisibility by rememberSaveable { mutableStateOf(false) }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri = uri.toString() }
    }

    var addressClient by remember {
        mutableStateOf(listOf(ClientAddress()))
    }

    val callAddressClient = RetrofitFactory.getAddressClient().getAddressClient(idClient)

    callAddressClient.enqueue(object : Callback<ClientAddressList> {
        override fun onResponse(
            call: Call<ClientAddressList>, response: Response<ClientAddressList>
        ) {
            addressClient = response.body()!!.endereco_cliente
        }

        override fun onFailure(
            call: Call<ClientAddressList>, t: Throwable
        ) {

            Log.e("ERROR", "onFailure: ${t.message}")

        }

    })

    fun editProfile(
        idClient: Int,
        nome: String,
        email: String,
        password: String,
        cpf: String,
        photo: String,
        phone: String,
        cep: String,
        street: String,
        complement: String,
        neighbor: String,
        city: String,
        number: Int,
        uf: String

    ) {
        val editProfile = EditProfileRepository()

        lifecycleScope.launch {
            val response = editProfile.editProfile(
                idClient,
                nome,
                email,
                password,
                cpf,
                photo,
                phone,
                cep,
                street,
                complement,
                neighbor,
                city,
                number,
                uf
            )

            if (response.isSuccessful) {
                Toast.makeText(context, "Edited com success", Toast.LENGTH_SHORT).show()
                localStorage.saveDataString(context, photo, "photoClient")
            } else {
                Log.e("ERROR", "editProfile: $response")
            }

        }
    }

    val painter by remember {
        mutableIntStateOf(R.drawable.perfil)
    }




    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            HeaderEditProfile(text = stringResource(id = R.string.edit_profile), navController = navController)

            if (imageUri == "") {
                AsyncImage(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(shape = CircleShape),
                    model = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image Profile"
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(shape = CircleShape),
                    model = imageUri,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image Profile"
                )

            }

            Text(
                text = stringResource(id = R.string.edit_photo),
                color = Color(72, 138, 39),
                fontFamily = fontFamily,
                modifier = Modifier.clickable {
                    launcher.launch("image/*")
                },
                fontSize = 15.sp
            )


            InputOutlineTextField(
                value = nameClient,
                onValueChange = { nameClient = it },
                label = stringResource(id = R.string.name),
                leadingIconImageVector = Icons.Default.PermIdentity,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                borderColor = Color(72, 138, 39),
                border = ShapeDefaults.Small,
                showError = false
            )

            InputOutlineTextField(
                value = cpfClient,
                onValueChange = { cpfClient = it },
                label = stringResource(id = R.string.cpf),
                leadingIconImageVector = Icons.Default.Numbers,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                borderColor = Color(72, 138, 39),
                border = ShapeDefaults.Small,
                showError = false
            )

            InputOutlineTextField(
                value = emailClient,
                onValueChange = { emailClient = it },
                label = stringResource(id = R.string.email),
                leadingIconImageVector = Icons.Default.AlternateEmail,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                borderColor = Color(72, 138, 39),
                border = ShapeDefaults.Small,
                showError = false
            )

            InputOutlineTextField(
                value = passwordClient,
                onValueChange = { passwordClient = it },
                label = stringResource(id = R.string.password),
                leadingIconImageVector = Icons.Default.Password,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                borderColor = Color(72, 138, 39),
                border = ShapeDefaults.Small,
                showError = false,
                isPasswordField = true,
                isPasswordVisible = isPasswordVisibility,
                onVisibilityChange = { isPasswordVisibility = it }
            )

            InputOutlineTextField(
                value = numberClient,
                onValueChange = { numberClient = it },
                label = stringResource(id = R.string.phone),
                leadingIconImageVector = Icons.Default.Phone,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                borderColor = Color(72, 138, 39),
                border = ShapeDefaults.Small,
                showError = false
            )


            SecondCustomButton(
                onClick = {
                   if(imageUri == verificationURI){
                       editProfile(
                           idClient,
                           nameClient,
                           emailClient,
                           passwordClient,
                           cpfClient,
                           imageUri,
                           numberClient,
                           addressClient[0].cep_cliente!!,
                           addressClient[0].logradouro_cliente!!,
                           addressClient[0].complemento_cliente!!,
                           addressClient[0].bairro_cliente!!,
                           addressClient[0].localidade_cliente!!,
                           addressClient[0].numero_endereco_cliente!!,
                           addressClient[0].uf_cliente!!
                       )
                   }else{
                       storageRef = storageRef.child(System.currentTimeMillis().toString())
                       imageUri.let {
                           it.let {
                               storageRef.putFile(it.toUri()).addOnCompleteListener { task ->
                                   if (task.isSuccessful) {
                                       storageRef.downloadUrl.addOnSuccessListener { uri ->
                                           val map = HashMap<String, Any>()
                                           map["pic"] = uri.toString()
                                           photoValue = map.toString()
                                           firebase.collection("images").add(map)

                                           //PUT PROFILE
                                           editProfile(
                                               idClient,
                                               nameClient,
                                               emailClient,
                                               passwordClient,
                                               cpfClient,
                                               photoValue.replace("{pic=", "").replace("}", ""),
                                               numberClient,
                                               addressClient[0].cep_cliente!!,
                                               addressClient[0].logradouro_cliente!!,
                                               addressClient[0].complemento_cliente!!,
                                               addressClient[0].bairro_cliente!!,
                                               addressClient[0].localidade_cliente!!,
                                               addressClient[0].numero_endereco_cliente!!,
                                               addressClient[0].uf_cliente!!
                                           )

                                       }
                                   }
                               }
                           }
                       }
                   }
                },
                text = stringResource(id = R.string.save)
            )

        }

    }

}



