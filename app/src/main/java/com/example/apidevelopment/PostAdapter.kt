package com.example.apidevelopment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apidevelopment.databinding.ItemBinding

class PostAdapter(val context: Context, val mList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(ItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val post = mList.get(position)
        holder.binding.id.setText(post.id.toString())
        holder.binding.title.setText("Title : " + post.title)
        holder.binding.body.setText("Message : " + post.body)
        holder.binding.root.setOnClickListener{
            val intent = Intent(context, PostDetailsActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("id", post.id)
            intent.putExtra("title", post.title)
            intent.putExtra("body", post.body)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class MyViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){

    }
}