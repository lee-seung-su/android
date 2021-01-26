package com.example.viewpagerfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(frag_a: fraga) : RecyclerView.Adapter<Holder>() {
    var main = frag_a

    var data :MutableList<item_class> = mutableListOf<item_class>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var deleteButton : Button = holder.itemView.findViewById(R.id.item_view_delete_button)
        deleteButton.setOnClickListener{
            data.removeAt(position)
            main.refresh()
        }
        var ind = data.get(position)
        holder.setData(ind)
    }

    override fun getItemCount(): Int {
        //Log.d("############3","getItemCount")
        return data.size
    }


}

class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun setData(i : item_class) {
        var image : ImageView = itemView.findViewById(R.id.item_view_image)
        var name : TextView = itemView.findViewById(R.id.item_view_name)
        var content : TextView = itemView.findViewById(R.id.item_view_content)
        var del : Button = itemView.findViewById(R.id.item_view_delete_button)
        image.setImageResource(i.image)
        name.text = i.name
        content.text = i.content
        if(i.draw == true){
            del.visibility = View.VISIBLE
        }
        else{del.visibility = View.INVISIBLE}
    }

}
