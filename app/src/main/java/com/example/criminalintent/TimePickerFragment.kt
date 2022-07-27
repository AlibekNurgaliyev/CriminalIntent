package com.example.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

class TimePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calender = Calendar.getInstance()
        val timeListener =
            TimePickerDialog.OnTimeSetListener { _: TimePicker, hours: Int, minutes: Int ->

//                val Ho = calender.set(Calendar.HOUR, hours)
//                val Ho1 = calender.set(Calendar.MINUTE, minutes)

                val resultDate: Date = GregorianCalendar(0,0,0,hours,minutes).time

                targetFragment?.let { fragment ->
                    (fragment as Callbacks).onTimeSelected(resultDate)
                }

            }

        val date = arguments?.getSerializable(ARG_TIME) as Date

        calender.time = date
        val initialHours = calender.get(Calendar.HOUR)
        val initialMinutes = calender.get(Calendar.MINUTE)


        return TimePickerDialog(
            requireContext(),
            timeListener,
            initialHours,
            initialMinutes,
            true
        )
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("HH:mm")
        return format.format(date)
    }

    companion object {
        private const val ARG_TIME = "time"

        fun newInstance(time: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TIME, time)

            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }

    interface Callbacks {
        fun onTimeSelected(date: Date)
    }
}