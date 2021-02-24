package org.techtown.sports

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class HabitRecyclerViewAdapter(showhabit: ShowHabit) : RecyclerView.Adapter<Holder>() {
    var showHabitInstance = showhabit
    var helper : SqliteHelper?= null
    var data : MutableList<habit_item> = mutableListOf<habit_item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        var item = holder.itemView
        var itemTitle = data.get(position).title
        item.setOnClickListener{
            var intent = Intent(item.context, ShowCalendar::class.java)
            intent.putExtra("itemTitle",itemTitle)

            item.context.startActivity(intent)
        }
        item.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {

                var alert : AlertDialog.Builder = AlertDialog.Builder(item.context)
                alert.setTitle("수정 / 삭제")
                alert.setNegativeButton("수정", null)
                alert.setPositiveButton("삭제",DialogInterface.OnClickListener({
                    dialog, which ->helper!!.deleteHabit(data.get(position))
                    showHabitInstance.changeData()
                }))
                alert.show()
                return true
            }
        })
        var seekBar : SeekBar = holder.itemView.findViewById(R.id.item_seekBar)
        var itemCheckButton : ImageButton = holder.itemView.findViewById(R.id.item_check_button)
        var itemLeftText : TextView = holder.itemView.findViewById(R.id.item_left_text)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                seekBarValue.text = "${seekBar!!.progress}회 / ${seekBar.max}회"
                itemLeftText.setText("잔여 ${seekBar!!.max - seekBar.progress}회")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        itemCheckButton.setOnClickListener{
            seekBar.incrementProgressBy(1)
            itemLeftText.setText("잔여 ${seekBar!!.max - seekBar.progress}회")
        }
        var stopwatchButton :ImageView = item.findViewById(R.id.item_stopwatch_image)
        stopwatchButton.setOnClickListener{
            var next = StopWatch()
            var tempActivity = item.context as MainActivity
            var trans = tempActivity.supportFragmentManager.beginTransaction()
            trans.add(R.id.frame_layout,next)
            trans.addToBackStack("ShowHabit")
            //trans.hide(this)
            trans.commit()
        }
        var ind = data.get(position)
        holder.setData(ind)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var habitTitle : TextView = itemView.findViewById(R.id.item_habit_title)
    var habitDDay : TextView = itemView.findViewById(R.id.item_habit_dday)
    var habitSchedule : TextView = itemView.findViewById(R.id.item_habit_schedule)
    var habitResult : TextView = itemView.findViewById(R.id.item_habit_result)
    var itemCount : TextView = itemView.findViewById(R.id.item_count)
    var itemSeekBar : SeekBar = itemView.findViewById(R.id.item_seekBar)
    var stopWatchButton : ImageView = itemView.findViewById(R.id.item_stopwatch_image)
    var itemLeftText : TextView = itemView.findViewById(R.id.item_left_text)
    //var itemSeekBarValue : TextView = itemView.findViewById(R.id.item_seekBar_value)
    fun setData(item : habit_item){
        habitTitle.text = "${item.title}"
        habitDDay.text = "${item.dday}"
        habitSchedule.text = "수행기간 : 2020.03.10 ~ 2020.08.10"
        habitResult.text = "진행상태 : 완료"
        itemCount.text = "수행 횟수  "
        habitResult.visibility = View.GONE
        var temp = item.count.toString()
        var ch : Char
        var end = 0
        for(i in 0..temp.length-1){
            ch = temp.get(i)
            if(ch.toInt()>=48 && ch.toInt()<=57){
                continue
            }
            else{
                end = i
                break
            }
        }

        if(end != 0) {
            var count = temp.slice(IntRange(0, end - 1)).toInt()
            itemSeekBar.max = count
            itemLeftText.setText("잔여 ${count.toString()}회")
        }
        else{
            itemSeekBar.max = 123
            itemLeftText.setText("잔여 123회")
        }
        if(habitDDay.text.equals("")){
            habitDDay.visibility = View.GONE
            habitSchedule.visibility = View.VISIBLE
        }
        else {
            habitDDay.visibility = View.VISIBLE
            habitSchedule.visibility = View.GONE
        }
        if(item.use_stopwatch == 1){
            stopWatchButton.visibility = View.VISIBLE
        }
        else{
            stopWatchButton.visibility = View.GONE
        }
        Log.d("@#@#@#@#@@@@@@","2222  ${habitTitle.text}  ${item.use_stopwatch}")

    }

}
