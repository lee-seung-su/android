package org.techtown.sports

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HabitSelectImage.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitSelectImage : Fragment(),View.OnClickListener {
    lateinit var title : TextView
    lateinit var nextButton : Button
    lateinit var backButton : Button
    lateinit var spinner : Spinner
    lateinit var helper : SqliteHelper
    lateinit var selectedImage : ImageView

    var item: MutableList<String> = mutableListOf<String>("--이미지 선택--", "물", "금연", "일어나기", "계단오르기", "아침먹기")
    var spinnerImages : MutableList<Int> = mutableListOf<Int>(R.drawable.drinking_water, R.drawable.stop_smoking, R.drawable.standup, R.drawable.climbing_stairs, R.drawable.eat_breakfast)

    var mainActivity : MainActivity? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_habit_select_image, container, false)
        init(view)
        listen()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {

                Log.d("!!!!@@@@@#####","!@@!@  ${spinner.isSelected.toString()}")
                if(position == 0){
                    spinner.isSelected = false
                }
//                else if(position == 6){
//                    var intent = Intent(Intent.ACTION_GET_CONTENT)
//                    intent.setType("image/*")
//                    mainActivity!!.startActivityForResult(intent, 1234)
//                    Log.d("@#@#@#@#", "startActivityForResult!!")
//                }
                else {
                    selectedImage.setImageResource(spinnerImages.get(position - 1))
                    spinner.isSelected = true
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        return view
    }

    fun init(view: View){
        title = view.findViewById(R.id.habit_select_Image_title)
        spinner = view.findViewById(R.id.habit_select_image_spinner)
        nextButton = view.findViewById(R.id.habit_select_image_next_btn)
        backButton =view.findViewById(R.id.habit_select_image_back_btn)
        spinner.adapter = ArrayAdapter(mainActivity as Context, R.layout.support_simple_spinner_dropdown_item, item)
        selectedImage = view.findViewById(R.id.spinner_selected_image)
        //spinner.setSelection(0)

    }
    fun listen(){
        nextButton.setOnClickListener(this)
        backButton.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("@#@#@#","onActivityResult")
        if(requestCode == 1234){
            if(resultCode == RESULT_OK){

                var dataUri = data?.data
                selectedImage.setImageURI(dataUri)


            }
        }
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.habit_select_image_next_btn -> {

                if(spinner.isSelected == false){
                    Log.d("!!!!@@@@@#####","if~~~")
                    MySharedPreferences.setHabitImage(mainActivity as Context, R.drawable.default_image)
                }
                else{
                    Log.d("!!!!@@@@@#####","else~~~")
                    MySharedPreferences.setHabitImage(mainActivity as Context, spinnerImages.get(spinner.selectedItemPosition-1))
                }
                /*var next = ShowHabit()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.addToBackStack("HabitSelectImage")
                trans.hide(this)
                trans.add(R.id.frame_layout, next)
                trans.commit()*/


                var next = ShowHabit()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.commit()
            }
            R.id.habit_select_image_back_btn -> {
                mainActivity!!.onBackPressed()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        helper = SqliteHelper(mainActivity as Context, "habit", 1)
    }

}
