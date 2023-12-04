package br.senai.sp.saveeats.editprofile.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.CustomButton
import br.senai.sp.saveeats.ui.theme.fontFamily
import coil.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

@Composable
fun EditProfileScreen(localStorage: Storage) {

    var storageRef: StorageReference = FirebaseStorage.getInstance().reference.child("images")
    val fibaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    val context = LocalContext.current

    var imageUri by remember {
        mutableStateOf("")
    }

    var valorFoto by remember {
        mutableStateOf("")
    }

    imageUri = localStorage.readDataString(context, "photoClient").toString()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri = uri.toString() }
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
                    .height(220.dp),
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

                CustomButton(
                    onClick = {

                        storageRef = storageRef.child(System.currentTimeMillis().toString())

                        imageUri.let{
                            it.let {
                                storageRef.putFile(it.toUri()).addOnCompleteListener{task ->
                                    if (task.isSuccessful){
                                        storageRef.downloadUrl.addOnSuccessListener {uri ->
                                            val map = HashMap<String, Any>()
                                            map["pic"] = uri.toString()
                                            valorFoto = map.toString()
                                            fibaseFirestore.collection("images").add(map)
                                            //PUT AVALIATION

                                        }
                                    }
                                }
                            }
                        }


                    }
                    ,text = "EDIT PHOTO"
                )

            }

        }

    }

}
