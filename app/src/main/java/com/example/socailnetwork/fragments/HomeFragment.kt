package com.example.socailnetwork.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.socailnetwork.R
import com.example.socailnetwork.adapter.PostAdapter
import com.example.socailnetwork.data.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val db = FirebaseFirestore.getInstance()
    private val mAdapter = PostAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv1.adapter = mAdapter
        getAllPosts()
    }

    private fun getAllPosts(){
        val result : MutableList<Post> = mutableListOf()
        db.collection("posts").addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(requireContext(), error.message.toString(), Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            db.collection("posts").get().addOnSuccessListener {
                it.documents.forEach{doc->
                    val model = doc.toObject(Post::class.java)
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