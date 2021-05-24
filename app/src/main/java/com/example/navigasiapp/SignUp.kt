package com.example.navigasiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_signUp.setOnClickListener {
            addUser()
        }
        tvSignIn.setOnClickListener {
            goToLoginPage()
        }
        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setTitle("Sign Up Form")
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)
    }

    fun addUser(){
        var name = etUsername.text.toString()
        var password = etPassword.text.toString()
        var password2 = etPassword2.text.toString()
        if(password != password2){
            return Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_SHORT).show()
        }
        if(name.isEmpty() || password.isEmpty() || password2.isEmpty()){
            return Toast.makeText(this, "Masih ada field yang kosong", Toast.LENGTH_SHORT).show()
        }
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val checkUser = databaseHandler.checkUser(name)
        if(checkUser){
            Toast.makeText(this,"Data user sudah ada ", Toast.LENGTH_SHORT).show()
        }else{
            val status =
                databaseHandler.addUser(UserModel(0, name, password))
            if(status > -1) {
                Toast.makeText(this, "Data user tersimpan", Toast.LENGTH_LONG).show()
                etUsername.text.clear()
                etPassword.text.clear()
                etPassword2.text.clear()
            }else{
                Toast.makeText(this, "Data user gagal tersimpan", Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun goToLoginPage(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}