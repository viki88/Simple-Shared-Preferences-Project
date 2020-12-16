package com.purwadhika.sharedpreferencessimpleproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.purwadhika.sharedpreferencessimpleproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Keluar")
                .setMessage("Apakah Anda yakin ingin menghapus sesi ini?")
                .setPositiveButton(android.R.string.ok){ dialogInterface, _ ->
                    dialogInterface.dismiss()
                    clearUsernameAndPassword()
                    Toast.makeText(this, "Hapus sesi berhasil", Toast.LENGTH_SHORT).show()
                    goToLoginActivity()
                }
                .setNegativeButton(android.R.string.cancel){ dialogInterface, _ ->
                    dialogInterface.dismiss()
                }.create().show()
        }

        binding.textLabel.text = "Selamat datang, ${SharedPreferenceUtils.getStoredUsername(this)}"
    }

    private fun clearUsernameAndPassword(){
        val sharedPref = SharedPreferenceUtils.getSharedPreferences(this)
        with(sharedPref.edit()){
            putString(getString(R.string.username_key),"")
            putString(getString(R.string.password_key),"")
            apply()
        }
    }

    private fun goToLoginActivity(){
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}