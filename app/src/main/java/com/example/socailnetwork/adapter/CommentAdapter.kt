package com.example.socailnetwork.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socailnetwork.R
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun popMod(comment : Map<String, String>){
            itemView.tvComment.text = comment["comment_text"]
            itemView.tvCommentUsername.text = "@${comment["username"]}"
        }
    }

    var item: List<Map<String, String>> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.popMod(item[position])
    }
}