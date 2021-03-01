package org.techtown.sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.prolificinteractive.materialcalendarview.CalendarDay

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Memo.newInstance] factory method to
 * create an instance of this fragment.
 */
class Memo(date: CalendarDay) : Fragment() {
    lateinit var dateText : TextView
    lateinit var content : TextView
    var memoDate = date
    lateinit var memoLayout : LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_memo, container, false)
        init(view)
        dateText.text = "${memoDate.year}-${memoDate.month}-${memoDate.day}"

        return view
    }
    fun init(view:View){
        dateText = view.findViewById(R.id.memo_date)
        content = view.findViewById(R.id.memo_content)
        memoLayout = view.findViewById(R.id.memo_layout)
    }
    fun listen(){
        memoLayout.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }

        })
    }


}