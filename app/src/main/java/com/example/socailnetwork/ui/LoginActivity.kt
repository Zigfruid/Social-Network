package com.example.socailnetwork.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.socailnetwork.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private  val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login.setOnClickListener {
            if (username.text.isNotEmpty() && password.text.isNotEmpty()){
                loading.visibility = View.VISIBLE
                mAuth.signInWithEmailAndPassword(username.text.toString(), password.text.toString())
                    .addOnCompleteListener {
                    if(it.isSuccessful){
                        val currentUser = mAuth.currentUser
                        loading.visibility = View.GONE
                        updateUI(currentUser)

                    }else{
                        Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_LONG).show()
                        loading.visibility = View.GONE
                    }
                }
            } else {
                Toast.makeText(this, " Polyalardi toldirin'", Toast.LENGTH_LONG).show()
            }
        }
        register.setOnClickListener {
            val intent = Intent(this, RegistActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user : FirebaseUser?){
        if(user!=null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}