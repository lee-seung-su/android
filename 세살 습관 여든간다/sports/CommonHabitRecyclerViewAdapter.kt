package org.techtown.sports

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class CommonHabitRecyclerViewAdapter : RecyclerView.Adapter<CommonHolder>() {
    var helper : SqliteHelper?= null
    var data : MutableList<common_habit> = mutableListOf<common_habit>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.common_item_view,parent,false)
        return CommonHolder(view)
    }

    override fun onBindViewHolder(holder: CommonHolder, position: Int) {

        var item = holder.itemView
        var itemTitle = data.get(position).title
        var itemImage = data.get(position).habit_image
        item.setOnClickListener{
            helper!!.insertHabit(habit_item(itemTitle,"D-100","7회",0,"0-0-0",itemImage, 0, 0, "",
                "오전 9시 00분", 3, 1, "오후 11시 00분","오전 8시 00분"))
            var intent = Intent(holder.itemView.context, MainActivity::class.java)
            var main = holder.itemView.context as AnotherMain
            main.finish()
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            holder.itemView.context.startActivity(intent)
        }

        var ind = data.get(position)
        holder.setData(ind)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class CommonHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var image : ImageView = itemView.findViewById(R.id.common_habit_image)
    var title : TextView = itemView.findViewById(R.id.common_habit_title)

    fun setData(item:common_habit){
        image.setBackgroundResource(item.habit_image)
        title.text = item.title
    }

}
