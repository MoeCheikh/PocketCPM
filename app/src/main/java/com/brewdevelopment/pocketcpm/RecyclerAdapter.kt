package com.brewdevelopment.pocketcpm

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


/**
 * Created by Osama on 2017-08-16.
 */
class RecyclerAdapter(val recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{


    val titles= arrayOf("hdjuad","djiajhd","hjdaihd", "djhaidhj")
    val descr=arrayOf("hdjuad","djiajhd","hjdaihd", "djhaidhj")
    class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView){
       val currentItem: Int=0


        val  imageview= itemView?.findViewById(R.id.Pic) as ImageView
        val item_Title= itemView?.findViewById(R.id.Title) as TextView
        val item_Desc= itemView?.findViewById(R.id.Desc) as TextView





    }

    override  fun onCreateViewHolder(parent: ViewGroup?, i: Int): ViewHolder {
        val v: View?= LayoutInflater.from(parent?.context).inflate(R.layout.card_layout, parent,false)
        val viewHolder: ViewHolder= ViewHolder(v)
        return viewHolder

    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.item_Title?.text= titles[position]
        holder?.item_Desc?.text= descr[position]

    }
    override fun getItemCount(): Int {
        return titles.size
    }
}