package com.asadbek.dialogs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    lateinit var btnAlertDialog:Button
    lateinit var btnSnackBar:Button
    lateinit var myTextView:TextView
    lateinit var btnDatePicker:Button
    lateinit var btnTimePicker:Button
    lateinit var btnBottomSheetDialog:Button
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAlertDialog = findViewById(R.id.btnAlertDialog)
        btnSnackBar = findViewById(R.id.btnSnackBar)
        btnDatePicker = findViewById(R.id.btnDatePicker)
        myTextView = findViewById(R.id.myTextView)
        btnTimePicker = findViewById(R.id.btnTimePicker)
        btnBottomSheetDialog = findViewById(R.id.btnBottomShett)


        btnAlertDialog.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Xabarni o`chirish")
            alertDialog.setMessage("Rostanxam ushbu ma`lumotni o`chirmoqchimisiz?")
            alertDialog.setPositiveButton("O`chirish",object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    alertDialog.setCancelable(true)
                }

            })
            alertDialog.setNegativeButton("Bekor qilish",object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    alertDialog.setCancelable(true)
                }
            })

            alertDialog.show()

        }

        btnSnackBar.setOnClickListener {
            val snackbar = Snackbar.make(it,"O`chirilgan ma`lumotni tiklash",Snackbar.LENGTH_LONG)
            snackbar.setAction("Tasdiqlash",object :View.OnClickListener{
                override fun onClick(v: View?) {
                    Toast.makeText(this@MainActivity, "Tiklandi!", Toast.LENGTH_SHORT).show()
                }
            })
            snackbar.show()
        }

        btnDatePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this)
            datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this, "${dayOfMonth}.${month+1}.$year", Toast.LENGTH_SHORT).show()
                myTextView.text = "Kun: $dayOfMonth Oy: ${month+1} Yil: $year"
            }
            datePickerDialog.show()
        }

        btnTimePicker.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this,
                object : TimePickerDialog.OnTimeSetListener{
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        Toast.makeText(this@MainActivity, "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
                    }
                },24,60,true)

            timePickerDialog.updateTime(LocalTime.now().hour,LocalTime.now().minute)
            timePickerDialog.updateTime(12,50)
            timePickerDialog.show()

        }

        btnBottomSheetDialog.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(layoutInflater.inflate(R.layout.item_bottom_sheets_dialog,null,false))
            bottomSheetDialog.findViewById<Button>(R.id.btnClose)?.setOnClickListener {
                bottomSheetDialog.hide()
            }
            bottomSheetDialog.show()
        }

    }
}