package com.purwadhika.sharedpreferencessimpleproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.purwadhika.sharedpreferencessimpleproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding :ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            if (isValidInput()){
                saveUsernameData(binding.inputUsername.text.toString())
                savePasswordData(binding.inputPassword.text.toString())
                finish()
                Toast.makeText(this@RegisterActivity, "Register berhasil", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUsernameData(username :String){
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()){
            putString(getString(R.string.username_key), username)
            apply()
        }
    }

    private fun savePasswordData(password :String){
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()){
            putString(getString(R.string.password_key), password)
            apply()
        }
    }

    private fun isValidInput() :Boolean{
        return when{
            binding.inputUsername.text.toString().isEmpty() -> {
                Toast.makeText(this, "Username tidak boleh kosong.", Toast.LENGTH_SHORT).show()
                false
            }
            binding.inputPassword.text.toString().isEmpty() -> {
                Toast.makeText(this, "Password tidak boleh kosong.", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }
}