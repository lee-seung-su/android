package com.example.storyboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import kotlin.concurrent.thread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TendencyResult.newInstance] factory method to
 * create an instance of this fragment.
 */
class TendencyResult : Fragment(), View.OnClickListener {
    var mainActivity: MainActivity? = null
    lateinit var waitText: TextView
    lateinit var titleText: TextView
    lateinit var detailText: TextView
    lateinit var againButton: Button
    lateinit var saveButton: Button
    lateinit var progressBar: ProgressBar
    lateinit var famousImage : ImageView
    var handler: Handler = Handler(Looper.getMainLooper())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_tendency_result, container, false)
        init(view)
        listen()



        return view
    }


    fun makeMbtiType(): String {
        var str: String = ""
        if (mainActivity!!.ei_value > 0) {
            str += "E"
        } else {
            str += "I"
        }
        if (mainActivity!!.sn_value > 0) {
            str += "S"
        } else {
            str += "N"
        }
        if (mainActivity!!.tf_value > 0) {
            str += "T"
        } else {
            str += "F"
        }
        if (mainActivity!!.jp_value > 0) {
            str += "J"
        } else {
            str += "P"
        }
        return str
    }
    fun makeMbtiDetail(str : String){
        if(str == "ESTJ"){
            detailText.text = getString(R.string.estj_detail)
        }
        else if(str=="ESTP"){
            detailText.text = getString(R.string.estp_detail)
        }
        else if(str=="ESFJ"){
            detailText.text = getString(R.string.esfj_detail)
        }
        else if(str=="ESFP"){
            detailText.text = getString(R.string.esfp_detail)
        }
        else if(str=="ENTJ"){
            detailText.text = getString(R.string.entj_detail)
        }
        else if(str=="ENTP"){
            detailText.text = getString(R.string.entp_detail)
        }
        else if(str=="ENFJ"){
            detailText.text = getString(R.string.enfj_detail)
        }
        else if(str=="ENFP"){
            detailText.text = getString(R.string.enfp_detail)
        }
        else if(str=="ISTJ"){
            detailText.text = getString(R.string.istj_detail)
        }
        else if(str=="ISTP"){
            detailText.text = getString(R.string.istp_detail)
        }
        else if(str=="ISFJ"){
            detailText.text = getString(R.string.isfj_detail)
        }
        else if(str=="ISFP"){
            detailText.text = getString(R.string.isfp_detail)
        }
        else if(str=="INTJ"){
            detailText.text = getString(R.string.intj_detail)
        }
        else if(str=="INTP"){
            detailText.text = getString(R.string.intp_detail)
            famousImage.setImageResource(R.drawable.einstein)
        }
        else if(str=="INFJ"){
            detailText.text = getString(R.string.infj_detail)
        }
        else if(str=="INFP"){
            detailText.text = getString(R.string.infp_detail)
        }
    }
    fun init(view: View) {
        progressBar = view.findViewById(R.id.tendency_progressbar)
        waitText = view.findViewById(R.id.tendency_wait_text)
        titleText = view.findViewById(R.id.tendency_result_title)
        detailText = view.findViewById(R.id.tendency_result_detail)
        againButton = view.findViewById(R.id.tendency_test_again_button)
        saveButton = view.findViewById(R.id.tendency_result_save_button)
        famousImage = view.findViewById(R.id.tendency_result_famous_image)
    }

    fun listen() {
        againButton.setOnClickListener(this)
        saveButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when (id) {
            R.id.tendency_test_again_button -> {
                var next: CheckTendency = CheckTendency()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.hide(this)
                trans.addToBackStack("Tendency Result")
                trans.commit()

            }
            R.id.tendency_result_save_button -> {
                var intent = Intent(mainActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                mainActivity!!.finish()
            }
        }
    }

    fun goNext() {
        var next: SelectMenu = SelectMenu()
        var trans = mainActivity!!.supportFragmentManager.beginTransaction()
        trans.add(R.id.frame_layout, next)
        trans.hide(this)
        trans.addToBackStack("Tendency Result")
        trans.commit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        var t = thread(start = true) {

            Thread.sleep(2000)

            handler.post(Runnable() {
                run {
                    var str: String = makeMbtiType()
                    makeMbtiDetail(str)
                    progressBar.visibility = View.GONE
                    waitText.visibility = View.GONE
                    titleText.text = "당신의 성향은 ${str}입니다"
                    titleText.visibility = View.VISIBLE
                    detailText.visibility = View.VISIBLE
                    againButton.visibility = View.VISIBLE
                    saveButton.visibility = View.VISIBLE
                    famousImage.visibility = View.VISIBLE
                }

            })
        }
    }
}