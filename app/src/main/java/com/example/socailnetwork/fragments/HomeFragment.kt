package com.example.socailnetwork.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.socailnetwork.R
import com.example.socailnetwork.adapter.PostAdapter
import com.example.socailnetwork.data.Post
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.rv_item.*
import kotlin.time.measureTimedValue


class HomeFragment : Fragment(R.layout.home_fragment) {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val mAdapter = PostAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv1.adapter = mAdapter
        getAllPosts()

    }

    private fun getAllPosts(){
        val result : MutableList<Post> = mutableListOf()
        db.collection("users").document(mAuth.currentUser!!.uid).get()
            .addOnSuccessListener {
              val a = it.get("username").toString()
        db.collection("posts").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(requireContext(), error.message.toString(), Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            db.collection("posts").get().addOnSuccessListener { i ->
                i.documents.forEach { doc ->
                    val model = doc.toObject(Post::class.java)
                    model?.username = a
                    model?.let {
                        result.add(model)
                    }
                }
                mAdapter.item = result
                Log.d("maglumat", result.toString())
                }
            }
        }
    }
}