package com.example.socailnetwork.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.socailnetwork.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_post_fragment.*

class AddPostFragment : Fragment(R.layout.add_post_fragment) {
    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddPost.setOnClickListener {
            setLoading(true)
            if (!etAddTextPost.text.isNullOrEmpty()){
                val map: MutableMap<String, Any> = mutableMapOf()
                db.collection("posts").document()
                map["userId"] = mAuth.currentUser?.uid.toString()
                map["text"] = etAddTextPost.text.toString()
                map["like"] = 0
                map["dislike"] = 0
                map["comments"] = arrayListOf<String>()
                db.collection("posts").document().set(map)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Your post published", Toast.LENGTH_SHORT).show()
                        setLoading(false)

                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                        setLoading(false)

                    }

            }
        }


    }

    private fun setLoading(isLoading : Boolean){
        progress_bar_addPost.visibility = if(isLoading) View.VISIBLE else View.GONE
        etAddTextPost.isEnabled = isLoading
        btnAddPost.isEnabled = isLoading
    }
}