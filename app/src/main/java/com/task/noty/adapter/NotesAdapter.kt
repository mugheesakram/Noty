package com.task.noty.adapter

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.task.noty.R
import com.task.noty.models.database.Notes
import kotlinx.android.synthetic.main.row_notes.view.*

class NotesAdapter (private val context:FragmentActivity,
                    internal var notes:List<Notes>,
                    internal var itemClickListener:ItemClickListener): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_notes, parent, false)
        return NotesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.cv_notes.setOnClickListener{
            itemClickListener.onItem(position)
        }

        holder.tv_notes.text=notes.get(position).notesText


    }


    inner class NotesViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        internal var cv_notes:CardView=view.findViewById(R.id.cd_notes)
        internal var tv_notes:TextView=view.findViewById(R.id.tv_notes)
    }

    interface ItemClickListener {
        fun onItem(position: Int)
    }

}