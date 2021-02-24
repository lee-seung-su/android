package org.techtown.sports

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HabitAlternate.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitAlternate : Fragment(), View.OnClickListener {
    lateinit var title: TextView
    lateinit var question: TextView
    lateinit var answer: EditText
    lateinit var nextButton: Button
    lateinit var backButton: Button
    lateinit var spinner: Spinner
    lateinit var checkBox : CheckBox
    var item: MutableList<String> = mutableListOf<String>("--단위를 선택하세요--", "회", "개", "분", "시간")

    var mainActivity: MainActivity? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_habit_alternate, container, false)
        init(view)
        listen()

        return view
    }

    fun init(view: View) {
        title = view.findViewById(R.id.habit_alternate_title)
        question = view.findViewById(R.id.habit_alternate_question)
        answer = view.findViewById(R.id.habit_alternate_answer)
        nextButton = view.findViewById(R.id.habit_alternate_next_btn)
        backButton = view.findViewById(R.id.habit_alternate_back_btn)
        spinner = view.findViewById(R.id.habit_alternate_unit_spinner)
        spinner.adapter = ArrayAdapter(
            mainActivity as Context,
            R.layout.support_simple_spinner_dropdown_item,
            item
        )
        answer.setText("5")
        spinner.setSelection(1)
        checkBox = view.findViewById(R.id.habit_alternate_checkbox)

    }

    fun listen() {
        nextButton.setOnClickListener(this)
        backButton.setOnClickListener(this)
        checkBox.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when (id) {
            R.id.habit_alternate_next_btn -> {
                if(checkBox.isChecked == true && answer.text.toString().equals("")){
                    mainActivity!!.makeToast(mainActivity as Context, "횟수를 입력하세요!!")
                }
                else {
                    MySharedPreferences.setHabitAlternate(
                        mainActivity as Context,
                        "${answer.text.toString()} ${spinner.selectedItem.toString()}"
                    )

                    var next = HabitSelectImage()
                    var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                    trans.addToBackStack("HabitAlternate")
                    trans.hide(this)
                    trans.add(R.id.frame_layout, next)
                    trans.commit()
                }

            }
            R.id.habit_alternate_back_btn -> {
                mainActivity!!.onBackPressed()
            }
            R.id.habit_alternate_checkbox->{
                if(checkBox.isChecked){
                    question.visibility = View.VISIBLE
                    answer.visibility = View.VISIBLE
                    spinner.visibility = View.VISIBLE
                }
                else{
                    question.visibility = View.INVISIBLE
                    answer.visibility = View.INVISIBLE
                    spinner.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}