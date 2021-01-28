package com.example.viewpagerfragment

import android.content.Intent
import android.net.Uri
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import org.w3c.dom.Text

class Instagram_recyclerview_adapter(frag_b: fragb)  : RecyclerView.Adapter<InstaHolder>() {
    var main = frag_b
    var num = 0
    var postFlag=0
    var sendFlag=0
    var data : MutableList<instagram_item_class> = mutableListOf<instagram_item_class>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstaHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.instagram_item_view, parent, false)
        return InstaHolder(view)
    }

    override fun onBindViewHolder(holder: InstaHolder, position: Int) {
        var likesButton : Button = holder.itemView.findViewById(R.id.instagram_like_button)
        var likesNum :TextView = holder.itemView.findViewById(R.id.instagram_like_num)
        var postComments : TextView = holder.itemView.findViewById(R.id.instagram_comments)
        var commentButton :Button = holder.itemView.findViewById(R.id.instagram_comment_button)
        var sendButton :Button = holder.itemView.findViewById(R.id.instagram_send_button)
        var sendNum : EditText = holder.itemView.findViewById(R.id.instagram_send_id)
        likesButton.setOnClickListener{
            //likesButton.setBackgroundColor(Color.RED)
            likesNum.visibility=View.VISIBLE
            num++
            likesNum.text = "$num Likes"
        }
        commentButton.setOnClickListener{
            if(postFlag %2 == 0){
                postComments.visibility = View.VISIBLE
            }
            else{
                postComments.visibility = View.INVISIBLE
            }
            postFlag++
        }
        sendButton.setOnClickListener{
            if(sendFlag %2 == 0){
                sendNum.visibility = View.VISIBLE
                sendNum.setOnEditorActionListener{
                        textview, action, event ->

                    if (action == EditorInfo.IME_ACTION_DONE) {
                        var intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("smsto:${sendNum.text}")
                        //startActivity(intent)

                    }

                    true
                }
            }
            else{
                sendNum.visibility = View.INVISIBLE
            }
            sendFlag++
        }

        var ind = data.get(position)
        holder.setData(ind, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    /*fun listen(){
        likesButton.setOnClickListener{
            //likesButton.setBackgroundColor(Color.RED)
            likesNum.visibility=View.VISIBLE
            num++
            likesNum.text = "$num Likes"
        }
        commentButton.setOnClickListener{
            if(postFlag %2 == 0){
                postComments.visibility = View.VISIBLE
            }
            else{
                postComments.visibility = View.INVISIBLE
            }
            postFlag++
        }
        sendButton.setOnClickListener{
            if(sendFlag %2 == 0){
                sendNum.visibility = View.VISIBLE
                sendNum.setOnEditorActionListener{
                    textview, action, event ->

                        if (action == EditorInfo.IME_ACTION_DONE) {
                            var intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse("smsto:${sendNum.text}")
                            startActivity(intent)

                        }

                        true
                }
            }
            else{
                sendNum.visibility = View.INVISIBLE
            }
            sendFlag++
        }
    }*/
}

class InstaHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun setData(item: instagram_item_class, position:Int) {
        var profile_image : ImageView = itemView.findViewById(R.id.instagram_image)
        var name : TextView = itemView.findViewById(R.id.instagram_id)
        var image : ViewPager = itemView.findViewById(R.id.instagram_viewpager)
        if(position % 4==0) {
            profile_image.setImageResource(R.drawable.peach)
            name.text = item.name
            var adapter = InstagramAdapter()
            adapter.views = listOf(
                CustomImage1(itemView.context), CustomImage2(itemView.context), CustomImage3(itemView.context), CustomImage4(itemView.context)
            )
            image.adapter = adapter
        }
        else if((position %4) ==1){
            profile_image.setImageResource(R.drawable.lion)
            name.text = item.name
            var adapter = InstagramAdapter()
            adapter.views = listOf(
                CustomImage2(itemView.context), CustomImage4(itemView.context), CustomImage1(itemView.context), CustomImage3(itemView.context)
            )
            image.adapter = adapter
        }
        else if((position %4)==2){
            profile_image.setImageResource(R.drawable.duck)
            name.text = item.name
            var adapter = InstagramAdapter()
            adapter.views = listOf(
                CustomImage4(itemView.context), CustomImage2(itemView.context), CustomImage3(itemView.context),CustomImage1(itemView.context)
            )
            image.adapter = adapter
        }
        else{
            profile_image.setImageResource(R.drawable.rabbit)
            name.text = item.name
            var adapter = InstagramAdapter()
            adapter.views = listOf(
                CustomImage1(itemView.context), CustomImage3(itemView.context),CustomImage2(itemView.context), CustomImage4(itemView.context)
            )
            image.adapter = adapter
        }
    }

}
