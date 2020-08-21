package com.example.socailnetwork.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.socailnetwork.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_regist.*

class RegistActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)

        btnRegister.setOnClickListener {
            if (username.text.isNotEmpty() && password.text.isNotEmpty()){
                loading.visibility = View.VISIBLE
                mAuth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
                    .addOnCompleteListener {Task ->
                        if(Task.isSuccessful ){
                            val user = mAuth.currentUser
                            loading.visibility = View.GONE
                            updateUI(user)
                        }else {
                            loading.visibility = View.GONE
                            Toast.makeText(this, "Authentication is failed", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

    }
    private fun updateUI(user: FirebaseUser?){
        if(user!=null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}