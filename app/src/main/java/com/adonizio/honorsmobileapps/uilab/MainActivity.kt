package com.adonizio.honorsmobileapps.uilab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var tipSeekBar: SeekBar
    lateinit var button1: RadioButton
    lateinit var button2: RadioButton
    lateinit var button3: RadioButton
    lateinit var button4: RadioButton
    var subtotal = 0
    var tipPercent: Int = 0
    var numGuests:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1 = findViewById(R.id.radioButton)
        button2 = findViewById(R.id.radioButton2)
        button3 = findViewById(R.id.radioButton3)
        button4 = findViewById(R.id.radioButton4)
        subtotal = 100
        setUpRadioButtons()
        setUpTipSeekBar()
        setUpNumOfGuestsSpinner()
    }

    fun setUpTipSeekBar() {
        tipSeekBar = findViewById(R.id.seekBar)
        tipSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                tipPercent = tipSeekBar.progress
                setTipAndTotalTextViews()
                setRadioButtonAsChecked()
            }
        })
    }

    fun setTipAndTotalTextViews() {
        val tipView: TextView = findViewById(R.id.textView2)
        tipView.text = "$tipPercent%"
        val totalView: TextView = findViewById(R.id.textView8)
        totalView.text = "$${subtotal + (subtotal * tipPercent)/100}"
    }

    fun setUpRadioButtons() {
        val allButtons: List<View> = listOf(button1, button2, button3, button4)
        for (button in allButtons) {
            button.setOnClickListener { view ->
                when (view.id) {
                    R.id.radioButton -> tipPercent = 10
                    R.id.radioButton2 -> tipPercent = 15
                    R.id.radioButton3 -> tipPercent = 18
                    R.id.radioButton4 -> tipPercent = 25
                }
                tipSeekBar.progress = tipPercent
                setTipAndTotalTextViews()
            }
        }
    }

    fun setRadioButtonAsChecked() {
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup) as RadioGroup
        radioGroup.clearCheck()
        when (tipPercent) {
            10 -> {
                button1.isChecked = true
            }
            15 -> {
                button2.isChecked = true
            }
            18 -> {
                button3.isChecked = true
            }
            25 -> {
                button4.isChecked = true
            }
        }
    }
    fun setUpNumOfGuestsSpinner(){
        val guestSpinner:Spinner = findViewById(R.id.spinner)
        val guestsArrayAdapter = ArrayAdapter.createFromResource(this, R.array.num_guests, android.R.layout.simple_spinner_item)
        guestsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        guestSpinner.adapter = guestsArrayAdapter
        guestSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>, childView: View?, position: Int, itemId: Long) {
                numGuests = Integer.parseInt(adapterView.getItemIdAtPosition(position).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }


}