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
            //setLoading(true)
            progress_bar.visibility = View.VISIBLE
            btnSave.isEnabled = false
            etInformation.isEnabled = false
            etUserName.isEnabled = false
            etEmailAddress.isEnabled = false
            etPhoneNumber.isEnabled = false

            val map: MutableMap<String, Any> = mutableMapOf()
            map["username"] = etUserName.text.toString()
            map["email"] = etEmailAddress.text.toString()
            map["phone"] = etPhoneNumber.text.toString()
            map["info"] = etInformation.text.toString()
            db.collection("users").document(mAuth.currentUser!!.uid).set(map)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Профильге озгерис киритилди", Toast.LENGTH_SHORT).show()
           //         setLoading(false)
                    progress_bar.visibility = View.GONE
                    btnSave.isEnabled = true
                    etInformation.isEnabled = true
                    etUserName.isEnabled = true
                    etEmailAddress.isEnabled = true
                    etPhoneNumber.isEnabled = true
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                   //setLoading(false)
                    progress_bar.visibility = View.GONE
                    btnSave.isEnabled = true
                    etInformation.isEnabled = true
                    etUserName.isEnabled = true
                    etEmailAddress.isEnabled = true
                    etPhoneNumber.isEnabled = true
                }
        }
    }

//    private fun setLoading(isLoading : Boolean){
//        if (isLoading){
//            progress_bar.visibility = View.VISIBLE
//        }else{
//            etUserName.isEnabled = !isLoading
//            etPhoneNumber.isEnabled = !isLoading
//            etEmailAddress.isEnabled = !isLoading
//            etInformation.isEnabled = !isLoading
//            btnSave.isEnabled = !isLoading
//        }
//    }

   private fun showData(){
  //      setLoading(true)
       progress_bar.visibility = View.VISIBLE
       btnSave.isEnabled = false
       etInformation.isEnabled = false
       etUserName.isEnabled = false
       etEmailAddress.isEnabled = false
       etPhoneNumber.isEnabled = false

       db.collection("users").document(mAuth.currentUser!!.uid).get()
            .addOnSuccessListener {
                 etUserName.setText(it.get("username").toString())
                 etEmailAddress.setText(it.get("email").toString())
                 etPhoneNumber.setText(it.get("phone").toString())
                 etInformation.setText(it.get("info").toString())
          //  setLoading(false)
                progress_bar.visibility = View.GONE
                btnSave.isEnabled = true
                etInformation.isEnabled = true
                etUserName.isEnabled = true
                etEmailAddress.isEnabled = true
                etPhoneNumber.isEnabled = true
               }
    }
}