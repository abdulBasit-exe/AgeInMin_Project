package learning.appdev.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
        
         fun statusBarColor(){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                window.setStatusBarColor(resources.getColor(R.color.primaryColor,this.theme))
            }
            else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.setStatusBarColor(resources.getColor(R.color.primaryColor,this.theme))
                }
            }
        }
    }

    }
fun clickDatePicker(view: View){
    val myCalender= Calendar.getInstance()
    val year = myCalender.get(Calendar.YEAR)
    val month = myCalender.get(Calendar.MONTH)
    val day = myCalender.get(Calendar.DAY_OF_MONTH)




   val dpd=  DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
            view, selectedYear, selectedMonth, selectedDayOfMonth ->

        val selectedDate= "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
        tvSelectedDate.setText(selectedDate)

        val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

        val theDate = sdf.parse(selectedDate)

        val selectedDateInMinutes = theDate!!.time / 60000
        // the get time method returns the milliseconds. so deviding by 60000 to get minutes.

        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        val currentDateInMinutes = currentDate!!.time/60000

        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

        tvDisplayAgeInMinutes.setText(differenceInMinutes.toString())


    }

     ,year,month,day)

    dpd.datePicker.setMaxDate(Date().time-86400000)
    // setting the limit of the date picker, so any date from future can not be picked.
    dpd.show()

}
}
