package com.purwadhika.sharedpreferencessimpleproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.purwadhika.sharedpreferencessimpleproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding :ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            if (isValidInput()){
                SharedPreferenceUtils.storeUsername(this, binding.inputUsername.text.toString())
                SharedPreferenceUtils.storePassword(this, binding.inputPassword.text.toString())
                finish()
                Toast.makeText(this@RegisterActivity, "Register berhasil", Toast.LENGTH_SHORT).show()
            }
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