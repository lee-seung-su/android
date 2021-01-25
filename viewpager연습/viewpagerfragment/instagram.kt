package com.example.viewpagerfragment

import android.content.Context
import android.content.Intent
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
    lateinit var fragbViewPager : ViewPager
    lateinit var likesButton : Button
    lateinit var commentButton : Button
    lateinit var sendButton : Button
    lateinit var likesNum : TextView
    lateinit var postComments : TextView
    lateinit var sendNum : EditText
    var num = 0
    var postFlag=0
    var sendFlag=0
    var mainactivity : MainActivity? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_instagram, container, false)
        init(view)
        listen()
        var imagelist = listOf(fragimage1(), fragimage2(), fragimage3(), fragimage4())

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainactivity = context as MainActivity
    }

    fun listen(){
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
    }
    fun init(view:View){
        fragbViewPager = view.findViewById(R.id.fragb_viewpager)
        likesButton = view.findViewById(R.id.fragb_like_button)
        likesNum = view.findViewById(R.id.fragb_like_num)
        postComments = view.findViewById(R.id.fragb_comments)
        commentButton = view.findViewById(R.id.fragb_comment_button)
        sendButton = view.findViewById(R.id.fragb_send_button)
        sendNum = view.findViewById(R.id.fragb_send_id)
    }

}