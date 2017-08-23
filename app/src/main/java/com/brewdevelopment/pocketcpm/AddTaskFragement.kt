package com.brewdevelopment.pocketcpm


import android.app.Fragment
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.sql.Time
import java.util.*

/**
 * Created by neyon on 2017-07-29.
 * this fragment handels adding new tasks
 * gets passed the Activity of the task that called the fragment if it is being called for an edit
 */

class AddTaskFragement : Fragment(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    val START_DATE = "start_date"
    val END_DATE = "end_date"
    val START_TIME = "start_time"
    val END_TIME="end_time"


    lateinit var startDateView: EditText
    lateinit var endDateView: EditText
    lateinit var taskButton: Button

    lateinit var fragmentEventListener: FragmentEventListener


    companion object {
        val ADD_TASK = "add_task"
        val EDIT_TASK = "edit_task"
        fun newInstance(): Fragment{
            //creating a brand NEW task
            var fragment = AddTaskFragement()
            return fragment
        }

        fun newInstance(callingTask: Task): Fragment{
            //Being called from a task for an EDIT
            //create and pass the bundle containing the attributes of the task
            var fragment = AddTaskFragement()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater?.inflate(R.layout.fragement_add_task,container, false)
        startDateView = rootView!!.findViewById(R.id.start_date_field) as EditText
        endDateView = rootView!!.findViewById(R.id.end_date_field) as EditText


        taskButton = rootView!!.findViewById(R.id.task_button) as Button

        startDateView.setOnClickListener{
            //define the date pop up and right after define the time popup
            //we can use 'it' to refer to the calling view

            val now: Calendar = Calendar.getInstance()
            var dpd = DatePickerDialog.newInstance(this, now.get(Calendar.YEAR),now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
            dpd.show(fragmentManager, START_DATE)
            dpd.setVersion(DatePickerDialog.Version.VERSION_2)
        }

        endDateView.setOnClickListener {
            //define the date pop up and right after define the time popup
            //we can use 'it' to refer to the calling view

            val now: Calendar = Calendar.getInstance()
            var dpd = DatePickerDialog.newInstance(this, now.get(Calendar.YEAR),now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
            dpd.show(fragmentManager, END_DATE)
            dpd.setVersion(DatePickerDialog.Version.VERSION_2)
        }

        taskButton.setOnClickListener {
            //the save button has been clicked, store or append the data
            //build the task object

            val taskName = (rootView.findViewById(R.id.task_name_field) as EditText).text       //get task name
            var mTask: Task = Task()

        }
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(!(context is FragmentEventListener)) throw AssertionError()  //if the call activity has implemented AddFragmentEventListener continue
        fragmentEventListener = context         // casts the calling activity to the implementation on AddFragmentEventsListener

    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {

        if(view!!.tag == START_DATE){
            //START date entered
            Log.i("Dbg", view!!.tag)
            var startDate = "" + dayOfMonth
            startDate+= "/" + monthOfYear
            startDate+= "/" + year

            startDateView.setText(startDate)

            //get the start time
            val now: Calendar = Calendar.getInstance()
            val dpd = TimePickerDialog.newInstance(this, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true)
            dpd.show(fragmentManager,START_TIME)
            dpd.setVersion(TimePickerDialog.Version.VERSION_2)
        }else if (view!!.tag == END_DATE){
            //END date entered
            var endDate = "" + dayOfMonth
            endDate+= "/" + monthOfYear
            endDate+= "/" + year

            endDateView.setText(endDate)

            //get the start time
            val now: Calendar = Calendar.getInstance()
            val dpd = TimePickerDialog.newInstance(this, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true)
            dpd.show(fragmentManager,END_TIME)
            dpd.setVersion(TimePickerDialog.Version.VERSION_2)
        }
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {

        if(view!!.tag == START_TIME){
            //START time entered

        }else if(view!!.tag == END_TIME){
            //END time entered

        }
    }


}
