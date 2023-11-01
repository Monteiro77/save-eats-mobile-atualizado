package br.senai.sp.saveeats

import android.content.Context
import android.content.SharedPreferences

class Storage {

    fun saveDataString(context: Context, valor: String, nome: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(nome, valor)
        editor.apply()
    }

    fun saveDataInt(context: Context, valor: Int, nome: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(nome, valor)
        editor.apply()
    }

    fun saveDataFloat(context: Context, valor: Float, nome: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putFloat(nome, valor)
        editor.apply()
    }

    fun readDataInt(context: Context, nome: String): Int {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(nome, 0)
    }

    fun readDataString(context: Context, nome: String): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE)
        return sharedPreferences.getString(nome, null)
    }
    fun readDataFloat(context: Context, nome: String): Float {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE)
        return sharedPreferences.getFloat(nome, 0f)
    }

}