package com.example.kakao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CustomAdapter : RecyclerView.Adapter<Holder>() {
    var data : MutableList<item> = mutableListOf<item>()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val  i = data.get(position)
        holder.setdata(i)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.kakao_item, parent, false)
        return Holder(view)
    }
}

class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){

    fun setdata(i : item){
        var image : ImageView = itemView.findViewById(R.id.image)
        var name : TextView = itemView.findViewById(R.id.name)
        var content : TextView = itemView.findViewById(R.id.content)
        image.setImageResource(i.image)
        name.text = i.name
        content.text = i.content

    }

}
