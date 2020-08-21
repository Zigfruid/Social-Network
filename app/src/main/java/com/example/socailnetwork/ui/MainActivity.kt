package com.example.socailnetwork.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.socailnetwork.R
import com.example.socailnetwork.fragments.AccountFragment
import com.example.socailnetwork.fragments.AddPostFragment
import com.example.socailnetwork.fragments.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()
    private  val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db.collection("users").document(mAuth.currentUser?.uid!!.toString()).get()
            .addOnCompleteListener {
                if (it.isSuccessful && !it.result?.exists()!!){
                    val map: MutableMap<String, Any?> = mutableMapOf()
                    map["email"] = mAuth.currentUser?.email

                        db.collection("users").document(mAuth.currentUser?.uid!!).set(map)
                            .addOnSuccessListener {
                                Log.d("user", "User has been added successfully")
                            }
                            .addOnFailureListener {e->
                                Log.d("user", e.localizedMessage!!.toString())

                            }
                }
            }

        val homeFragment = HomeFragment()
        val addPostFragment = AddPostFragment()
        val accountFragment = AccountFragment()

        makeCurrent(homeFragment)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> makeCurrent( homeFragment)
                R.id.add_post -> makeCurrent(addPostFragment)
                R.id.profile -> makeCurrent(accountFragment)
            }
            true
        }

    }
    private fun makeCurrent(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment).commit()
        }
    }
}