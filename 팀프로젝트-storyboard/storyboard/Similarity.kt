package com.example.storyboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Similarity.newInstance] factory method to
 * create an instance of this fragment.
 */
class Similarity : Fragment() {
    lateinit var progressbar : ProgressBar
    lateinit var waitText : TextView
    lateinit var resultText : TextView
    var mainActivity : MainActivity ?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_similarity, container, false)
        init(view)

        return view
    }
    fun init(view : View){
        progressbar = view.findViewById(R.id.similarity_progressbar)
        waitText = view.findViewById(R.id.similarity_wait_text)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}