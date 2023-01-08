package com.android.dobcalculator


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity()
{   private var tv1:TextView?=null
    private var TVAM:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1=findViewById(R.id.tv1)
        TVAM=findViewById(R.id.TVAM)
       val btnDP=findViewById<Button>(R.id.btnDP)


        btnDP.setOnClickListener {
            onClickDate()

        }

    }
    private fun onClickDate()
    {
        val myCal=Calendar.getInstance()
        val year=myCal.get(Calendar.YEAR)
        val month=myCal.get(Calendar.MONTH)
        val day=myCal.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(applicationContext,
                    "$selectedYear/${selectedMonth + 1}/$selectedDayOfMonth",
                    Toast.LENGTH_LONG).show()
                val tvsel = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tv1?.text = tvsel
                val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate=sdf.parse(/* source = */ tvsel)
                val dateMin=theDate.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDMin=currentDate.time/60000
                val diffTimeMin=currentDMin-dateMin
                TVAM?.text=diffTimeMin.toString()

            },
            year,
            month,
            day,
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()

    }


}