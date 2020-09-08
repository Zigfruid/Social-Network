package com.example.socailnetwork.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.view.isNotEmpty
import com.example.socailnetwork.R
import com.example.socailnetwork.adapter.CommentAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : AppCompatActivity() {

    private val cAdapter = CommentAdapter()
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        rvCommentActivity.adapter = cAdapter
        var id = ""
        id = intent.getStringExtra("postId") ?: ""
        setComments(id)
        timer.start()
        progressBarComment.visibility = View.VISIBLE

    }
    private val timer = object : CountDownTimer(1000 , 1000){

        override fun onTick(p0: Long) {

        }
        override fun onFinish() {
            if (cAdapter.item.isNotEmpty()){
                rvCommentActivity.visibility = View.VISIBLE
                tvTitle.visibility = View.GONE
            }else{
                rvCommentActivity.visibility = View.GONE
                tvTitle.visibility = View.VISIBLE

            }
            progressBarComment.visibility = View.GONE
        }
    }
    private fun setComments(id: String) : String {
        var post = " "
        db.collection("posts").document(id).get()
            .addOnSuccessListener {
                if (it.exists()){
                    post = "yes"
                    it.get("comments")?.let {comment ->
                        cAdapter.item = comment as List<Map<String, String>>
                    }
                }
            }
        return post
    }
}