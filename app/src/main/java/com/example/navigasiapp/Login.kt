package com.example.navigasiapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tvSignUp.setOnClickListener {
            goToSignUpPage()
//            addProduct()
        }
//        addUser()
    }

    fun loginMasuk(view: View) {
        val Uname   = username.text.toString()
        val Upass   = password.text.toString()



        if (Uname.isEmpty() || Upass.isEmpty()){
            Toast.makeText(this, "Isikan Username atau Password Dahulu !", Toast.LENGTH_SHORT).show()
        } else {
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            val checkAuth: Boolean = databaseHandler.checkAuth(Uname, Upass)
            if (checkAuth){
                val getUser: Int = databaseHandler.getUser(Uname)
                sharedPref = getSharedPreferences("SharePref",Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPref.edit()
                editor.putBoolean("loginStatus", true)
                editor.putInt("idUser", getUser)
                editor.apply()
                Toast.makeText(this, "Login Sukses!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
//            true
            } else {
                Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    fun goToSignUpPage(){
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }

    fun addProduct(){
        val arrayItem = ArrayList<ProductModel>()
        arrayItem.add(ProductModel(1, "Jersey Home", "Exclusive Match", R.drawable.home ,800000))
        arrayItem.add(ProductModel(2,"Jersey Away", "Exclusive Match", R.drawable.away ,800000))
        arrayItem.add(ProductModel(3,"Jersey 3rd", "Exclusive Match", R.drawable.away2 ,750000))
        arrayItem.add(ProductModel(4,"Jersey El-Clasico Edition", "Exclusive Match", R.drawable.clasico ,1000000))
        arrayItem.add(ProductModel(5,"Jersey Training 1", "Supporters Edition", R.drawable.training ,500000))
        arrayItem.add(ProductModel(6,"Jersey Training 2", "Supporters Edition", R.drawable.training2 ,500000))
        arrayItem.add(ProductModel(7,"Jersey Training 3", "Supporters Edition", R.drawable.training3 ,500000))
        arrayItem.add(ProductModel(8,"Jaket Training", "Supporters Edition", R.drawable.jaket ,900000))
        arrayItem.add(ProductModel(9,"Jersey Kids Home", "Supporters Edition", R.drawable.kidshome ,600000))
        arrayItem.add(ProductModel(10,"Jersey Kids Away", "Supporters Edition", R.drawable.kidsaway ,600000))

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        arrayItem.forEach {
            databaseHandler.addProducts(ProductModel(0,it.nmProduct, it.dsProduct, it.picProduct, it.priceofProduct))
        }

    }
}