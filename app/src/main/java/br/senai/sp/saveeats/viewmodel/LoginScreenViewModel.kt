package br.senai.sp.saveeats.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val loading = MutableLiveData(false)

    fun signInWithGoogleCredential(credencial: AuthCredential, home: () -> Unit) =
        viewModelScope.launch{
            try {
                auth.signInWithCredential(credencial).addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        Log.d("LoginGoogle", "Logado com Sucesso ")
                        home()
                    }

                }
            }catch (ex: Exception){
                Log.d("Teste", "Error ao logar com google")
            }

    }
}