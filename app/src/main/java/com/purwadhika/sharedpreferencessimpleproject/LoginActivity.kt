package com.purwadhika.sharedpreferencessimpleproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.purwadhika.sharedpreferencessimpleproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding :ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            if (isValidInput()){
                if (binding.inputPassword.text.toString() == getSavedPassword() &&
                        binding.inputUsername.text.toString() == getSavedUsername()){
                    goToMainActivity()
                }else{
                    Toast.makeText(this, "Kombinasi Username dan Password salah silahkan ulangi lagi.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.signupButton.setOnClickListener { goToRegisterActivity() }

        // check if any username is saved go to main activity
        if (!getSavedUsername().isNullOrEmpty()) goToMainActivity()
    }

    private fun goToMainActivity() = startActivity(Intent(this, MainActivity::class.java))
    private fun goToRegisterActivity() = startActivity(Intent(this, RegisterActivity::class.java))


    private fun getSavedUsername() :String?{
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(getString(R.string.username_key), "")
    }

    private fun getSavedPassword() :String?{
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(getString(R.string.password_key), "")
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