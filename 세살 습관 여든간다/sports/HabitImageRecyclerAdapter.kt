package org.techtown.sports

import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class HabitImageRecyclerAdapter : RecyclerView.Adapter<ImageHolder>() {
    var helper : SqliteHelper?= null
    var data = mutableListOf<image_item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.image_item_view,parent,false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {

        var ind = data.get(position)
        holder.setData(ind)

        var item = holder.itemView
        var itemImage :ImageView = item.findViewById(R.id.image_item_id)

        item.setOnClickListener{
            var tempItem : image_item

            itemImage.setBackgroundColor(Color.parseColor("#8B8C8B"))
            MySharedPreferences.setHabitImage(holder.itemView.context, ind.image)
            var main = item.context as AnotherMain
            main.handler.postDelayed(Runnable {
                itemImage.setBackgroundColor(Color.WHITE)
            },1000)

        }
        if(MySharedPreferences.getHabitImage(holder.itemView.context).equals(-1)){
            MySharedPreferences.setHabitImage(holder.itemView.context, R.drawable.default_image)
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }



}

class ImageHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
    var id : ImageView = itemView.findViewById(R.id.image_item_id)
    fun setData(item:image_item){
        if(item.title.equals("물 마시기")){
            id.setImageResource(item.image)
        }
        else if(item.title.equals("계단 걷기")){
            id.setImageResource(item.image)
        }
        else if(item.title.equals("아침 먹기")){
            id.setImageResource(item.image)
        }
        else if(item.title.equals("일어나기")){
            id.setImageResource(item.image)
        }
        else if(item.title.equals("금연하기")){
            id.setImageResource(item.image)
        }
        else{
            id.setImageResource(item.image)
        }

    }


}
