package com.task.noty.adapter

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.task.noty.R
import com.task.noty.models.database.Users

class UserAdapter (private val context:FragmentActivity,
                   internal var users: List<Users>,
                   internal var itemClickListener:ItemClickListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_notes, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.cv_user.setOnClickListener{
            itemClickListener.onItem(position)
        }

        holder.tv_user.text=users.get(position).firstName


    }


    inner class UserViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        internal var cv_user: CardView =view.findViewById(R.id.cd_user)
        internal var tv_user: TextView =view.findViewById(R.id.tv_user)
    }

    interface ItemClickListener {
        fun onItem(position: Int)
    }

}