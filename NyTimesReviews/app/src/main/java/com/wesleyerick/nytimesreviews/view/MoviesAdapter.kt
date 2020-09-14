package com.wesleyerick.nytimesreviews.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wesleyerick.nytimesreviews.R
import com.wesleyerick.nytimesreviews.model.ItemCriticData
import kotlinx.android.synthetic.main.recycler_item.view.*

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var critics : ArrayList<ItemCriticData> = ArrayList()

    fun configData(criticsData: ArrayList<ItemCriticData>){
        this.critics.clear()
        this.critics.addAll(criticsData)
        this.notifyDataSetChanged()
        println("CRITICS DATA ===> ${critics}")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private lateinit var criticsListener : MoviesAdapter.CriticsListener


    interface CriticsListener {
        fun onItemClick(position: Int)
    }

    fun listener(listener: CriticsListener){
        this.criticsListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recycler_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        val critics = critics[position]

        holder.itemView.textTitle.text = critics.title
        holder.itemView.textDescription.text = critics.description
        holder.itemView.textCriticName.text = critics.criticName
        holder.itemView.btnCriticItem.setOnClickListener {
            criticsListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        println("CRITICS ===>> ${critics.size}")
        return critics.size
    }


}