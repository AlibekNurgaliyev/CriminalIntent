package com.example.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calender = Calendar.getInstance()
        val timeListener =
            TimePickerDialog.OnTimeSetListener { _: TimePicker, hours: Int, minutes: Int ->
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