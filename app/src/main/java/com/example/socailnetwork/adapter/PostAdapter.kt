package com.example.socailnetwork.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socailnetwork.R
import com.example.socailnetwork.data.Post
import kotlinx.android.synthetic.main.rv_item.view.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var item:List<Post> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    private var onItemClicked : (model:Post) -> Unit = {}
    fun setOnItemClickListener(onItemClicked:(model:Post) -> Unit){
        this.onItemClicked = onItemClicked
    }

    private var onCommentClicked: (model: Post) -> Unit = {}
    fun setOnCommentClickListener(onCommentClicked: (model: Post) -> Unit){
        this.onCommentClicked = onCommentClicked
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun popMod(data: Post){
            itemView.tvUserName.text = data.username
            itemView.tvDescription.text = data.theme
            itemView.tvPost.text = data.text
            itemView.setOnClickListener {
                onItemClicked.invoke(data)
            }
            itemView.btnComment.setOnClickListener {
                onCommentClicked.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.popMod(item[position])
    }

}