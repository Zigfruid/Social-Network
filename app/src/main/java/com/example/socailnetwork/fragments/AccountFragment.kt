package com.example.socailnetwork.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.socailnetwork.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.account_fragment.*


class AccountFragment : Fragment(R.layout.account_fragment) {
   private val db = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData()
        btnSave.setOnClickListener {
            val map: MutableMap<String, Any> = mutableMapOf()
            map["username"] = etUserName.text.toString()
            map["email"] = etEmailAddress.text.toString()
            map["phone"] = etPhoneNumber.text.toString()
            map["info"] = etInformation.text.toString()
            db.collection("users").document(mAuth.currentUser!!.uid).set(map)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Профильге озгерис киритилди", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()

                }
        }
    }

   private fun showData(){
        db.collection("users").document(mAuth.currentUser!!.uid).get()
            .addOnSuccessListener {
                 etUserName.setText(it.get("username").toString())
                 etEmailAddress.setText(it.get("email").toString())
                 etPhoneNumber.setText(it.get("phone").toString())
                 etInformation.setText(it.get("info").toString())
            }
    }
}