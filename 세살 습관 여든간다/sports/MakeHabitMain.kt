package org.techtown.sports

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Check.newInstance] factory method to
 * create an instance of this fragment.
 */
class MakeHabitMain : Fragment(),View.OnClickListener {
    lateinit var title : TextView
    lateinit var button : Button
    var mainActivity: MainActivity?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_make_habit_main, container, false)
        init(view)
        listen()

        return view
    }
    fun init(view : View){
        title = view.findViewById(R.id.main_title)
        button = view.findViewById(R.id.main_add_button)
    }
    fun listen(){
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.main_add_button->{
                var next = HabitName("add")
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.addToBackStack("Main")
                trans.hide(this)
                trans.add(R.id.frame_layout,next)
                trans.commit()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}