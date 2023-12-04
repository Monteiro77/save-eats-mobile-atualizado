package br.senai.sp.saveeats.editprofile.screen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.LifecycleCoroutineScope
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.CustomButton
import br.senai.sp.saveeats.components.InputOutlineTextField
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
fun EditProfileScreen(localStorage: Storage, lifecycleScope: LifecycleCoroutineScope) {

    var storageRef: StorageReference = FirebaseStorage.getInstance().reference.child("images")
    val fibaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    val context = LocalContext.current

    val idClient = localStorage.readDataInt(context, "idClient")

    var imageUri by remember {
        mutableStateOf(localStorage.readDataString(context, "photoClient").toString())
    }

    var valorFoto by remember {
        mutableStateOf("")
    }

    var nameClient by remember {
        mutableStateOf(localStorage.readDataString(context, "nameClient").toString())
    }


    var cpfClient by remember {
        mutableStateOf(localStorage.readDataString(context, "cpfClient").toString())
    }
    var passwordClient by remember {
        mutableStateOf(localStorage.readDataString(context, "passwordClient").toString())
    }
    var numberClient by remember {
        mutableStateOf(localStorage.readDataString(context, "phoneClient").toString())
    }
    var emailClient by remember {
        mutableStateOf(localStorage.readDataString(context, "emailClient").toString())
    }


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
        senha: String,
        cpf: String,
        foto: String,
        telefone: String,
        cep: String,
        logradouro: String,
        complemento: String,
        bairro: String,
        localidade: String,
        numero: Int,
        uf: String

    ) {
        val editarProfile = EditProfileRepository()

        lifecycleScope.launch {
            val response = editarProfile.editProfile(
                idClient,
                nome,
                email,
                senha,
                cpf,
                foto,
                telefone,
                cep,
                logradouro,
                complemento,
                bairro,
                localidade,
                numero,
                uf
            )

            if(response.isSuccessful){
                Toast.makeText(context, "Editado com sucesso", Toast.LENGTH_SHORT).show()
            }else{
                Log.e("ERRO", "editProfile: $response", )
            }

        }
    }




    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {

                AsyncImage(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(shape = CircleShape),
                    model = imageUri,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image Profile"
                )

                Surface(
                    modifier = Modifier
                        .width(140.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(50.dp),
                    color = colorResource(id = R.color.green_save_eats_light)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                launcher.launch("image/*")
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = stringResource(id = R.string.edit_photo),
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            color = colorResource(id = R.color.white)
                        )

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Arrow Right",
                            tint = colorResource(id = R.color.white)
                        )

                    }

                }


                OutlinedTextField(
                    value = nameClient,
                    onValueChange = {
                        nameClient = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.name))
                    },
                    leadingIcon = {
                        Icons.Default.PermIdentity
                    }
                )

                OutlinedTextField(
                    value = cpfClient,
                    onValueChange = {
                        cpfClient = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.cpf))
                    },
                    leadingIcon = {
                        Icons.Default.PermIdentity
                    }
                )

                OutlinedTextField(
                    value = emailClient,
                    onValueChange = {
                        emailClient = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.email))
                    },
                    leadingIcon = {
                        Icons.Default.PermIdentity
                    }
                )

                OutlinedTextField(
                    value = passwordClient,
                    onValueChange = {
                        passwordClient = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.password))
                    },
                    leadingIcon = {
                        Icons.Default.PermIdentity
                    }
                )
                OutlinedTextField(
                    value = numberClient,
                    onValueChange = {
                        numberClient = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.number))
                    },
                    leadingIcon = {
                        Icons.Default.PermIdentity
                    }
                )


                Button(
                    onClick = {

                        storageRef = storageRef.child(System.currentTimeMillis().toString())

                        imageUri.let {
                            it.let {
                                storageRef.putFile(it.toUri()).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        storageRef.downloadUrl.addOnSuccessListener { uri ->
                                            val map = HashMap<String, Any>()
                                            map["pic"] = uri.toString()
                                            valorFoto = map.toString()
                                            fibaseFirestore.collection("images").add(map)
                                            //PUT AVALIATION
                                            editProfile(
                                                idClient,
                                                nameClient,
                                                emailClient,
                                                passwordClient,
                                                cpfClient,  
                                                imageUri.replace("{pic=", " ").replace("}", " "),
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
                ){
                    Text(text = "Editar Perfil")
                }

            }

        }

    }

}
