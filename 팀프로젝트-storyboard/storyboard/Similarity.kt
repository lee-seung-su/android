package com.example.storyboard

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

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
    lateinit var similarityImage : ImageView
    lateinit var similarityDetailText : TextView
    lateinit var similarityHomeButton : Button
    var mainActivity : MainActivity?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_similarity, container, false)
        init(view)
        similarityHomeButton.setOnClickListener{
            var intent = Intent(mainActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        setSimiliarityInfo()
        return view
    }
    fun init(view:View){
        similarityImage = view.findViewById(R.id.similarity_image)
        similarityDetailText = view.findViewById(R.id.similarity_detail_text)
        similarityHomeButton = view.findViewById(R.id.similarity_home_button)
    }
    fun setSimiliarityInfo(){
        similarityImage.setImageResource(R.drawable.bear)
        similarityDetailText.text = "닮은 점\n생일 : 1월생\n취미 : 운동\n좋아하는 것 : 커피\n사는곳: 수원"
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}