package com.purwadhika.sharedpreferencessimpleproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.purwadhika.sharedpreferencessimpleproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding :ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            if (isValidInput()){
                if (binding.inputPassword.text.toString() == SharedPreferenceUtils.getStoredPassword(this) &&
                        binding.inputUsername.text.toString() == SharedPreferenceUtils.getStoredUsername(this)){
                    goToMainActivity()
                }else{
                    Toast.makeText(this, "Kombinasi Username dan Password salah silahkan ulangi lagi.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.signupButton.setOnClickListener { goToRegisterActivity() }

        // check if any username is saved go to main activity
        if (!SharedPreferenceUtils.getStoredUsername(this).isNullOrEmpty()) goToMainActivity()
    }

    private fun goToMainActivity(){
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun goToRegisterActivity() = startActivity(Intent(this, RegisterActivity::class.java))

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