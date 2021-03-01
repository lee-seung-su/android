package org.techtown.sports

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommonHabit.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommonHabit : Fragment() {
    lateinit var recycler : RecyclerView
    lateinit var adapter : CommonHabitRecyclerViewAdapter
    lateinit var data : MutableList<common_habit>
    lateinit var helper : SqliteHelper
    lateinit var mainActivity : AnotherMain
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_common_habit, container, false)
        init(view)
        return view
    }
    fun init(view : View){
        Log.d("@##@#@#","init")
        recycler = view.findViewById(R.id.common_habit_recycler)

        data = makeCommonHabit()
        adapter = CommonHabitRecyclerViewAdapter()
        adapter.data = data
        adapter.helper = helper
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(mainActivity)
        Log.d("@#@#@#@#","$data.size")
    }

    fun makeCommonHabit(): MutableList<common_habit> {
        var list = mutableListOf<common_habit>()
        list.add(common_habit("하루에 물 1L 마시기",R.drawable.person_pic))
        list.add(common_habit("영양제 챙겨먹기",R.drawable.heart_pic))
        list.add(common_habit("손 자주 씻기",R.drawable.like_pic))
        list.add(common_habit("하루 30분 홈 트레이닝",R.drawable.home_pic))
        list.add(common_habit("바보1",R.drawable.home_pic))
        list.add(common_habit("바보2",R.drawable.home_pic))
        return list
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as AnotherMain
        helper = SqliteHelper(mainActivity, "habit",1)
    }
}