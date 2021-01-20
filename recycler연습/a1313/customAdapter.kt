package com.example.a1313

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class customAdapter(mainActivity: MainActivity) : RecyclerView.Adapter<Holder>(){
    var context = mainActivity
    var data = mutableListOf<kakao_item>()
    var itemNum :TextView = context.findViewById(R.id.item_num)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var deleteButton : Button = holder.itemView.findViewById(R.id.delete_butt)

        deleteButton.setOnClickListener{
            val alert : AlertDialog.Builder = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("진짜 삭제할꺼?")
            alert.setNegativeButton("응", DialogInterface.OnClickListener{
                dialog, which -> data.removeAt(position)
                this.notifyDataSetChanged()
                itemNum.text = "현재 ${data.size}명"
            })
            alert.setPositiveButton("아니",null)
            alert.show()


        }

        var index = data.get(position)
        holder.setdata(index)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun setdata(item:kakao_item){
        var image1 : ImageView = itemView.findViewById(R.id.image_1)
        var name1 : TextView = itemView.findViewById(R.id.name_text)
        var content1 : TextView = itemView.findViewById(R.id.content_text)
        var flag : Button = itemView.findViewById(R.id.delete_butt)
        image1.setImageResource(item.image)
        name1.text = item.name
        content1.text = item.content
        if(item.draw_flag == true){
            flag.visibility = View.VISIBLE
        }
        else{
            flag.visibility = View.INVISIBLE
        }
    }


}
