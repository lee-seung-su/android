package com.example.viewpagerfragment

import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragb.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragb : Fragment() {
    lateinit var instagramRecyclerView : RecyclerView
    lateinit var instagramTitle : TextView

    var mainactivity : MainActivity? = null
    lateinit var views:List<View>
    lateinit var adapter : Instagram_recyclerview_adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_instagram, container, false)
        init(view)



        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainactivity = context as MainActivity
    }


    fun init(view:View){
        instagramRecyclerView = view.findViewById(R.id.instagram_recycler_view)
        instagramTitle = view.findViewById(R.id.instagram_title)

        adapter = Instagram_recyclerview_adapter(this)
        adapter.data = loadInstagramData()
        instagramRecyclerView.adapter = adapter
        instagramRecyclerView.layoutManager = LinearLayoutManager(mainactivity)

    }

    fun loadInstagramData(): MutableList<instagram_item_class> {
        var l : MutableList<instagram_item_class> = mutableListOf<instagram_item_class>()
        for(item in 1..10){
            var profileImage : Int? = null

            var name = "${item}입니다~"
            var feedImage : Int? = null

            var data = instagram_item_class(profileImage, name, feedImage)
            l.add(data)

        }
        return l

    }

}