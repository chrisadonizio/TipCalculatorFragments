package com.adonizio.honorsmobileapps.TipCalculatorFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.adonizio.honorsmobileapps.TipCalculatorFragments.databinding.FragmentMainBinding

private var _binding : FragmentMainBinding? = null
private val binding get() = _binding!!
class MainFragment : Fragment() {
    var subtotal = 0
    var tipPercent: Int = 0
    var numGuests:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root
        subtotal = 100
        setUpRadioButtons()
        setUpTipSeekBar()
        setUpNumOfGuestsSpinner()
        return rootView
    }
    fun setUpNumOfGuestsSpinner(){
        val guestSpinner: Spinner = binding.spinner
        val guestsArrayAdapter = ArrayAdapter.createFromResource(requireActivity(), R.array.num_guests, android.R.layout.simple_spinner_item)
        guestsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        guestSpinner.adapter = guestsArrayAdapter
        guestSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>, childView: View?, position: Int, itemId: Long) {
                numGuests = Integer.parseInt(adapterView.getItemIdAtPosition(position).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }
    fun setRadioButtonAsChecked() {
        val radioGroup: RadioGroup = binding.radioGroup as RadioGroup
        radioGroup.clearCheck()
        when (tipPercent) {
            10 -> {
                binding.radioButton.isChecked = true
            }
            15 -> {
                binding.radioButton2.isChecked = true
            }
            18 -> {
                binding.radioButton3.isChecked = true
            }
            25 -> {
                binding.radioButton4.isChecked = true
            }
        }
    }
    fun setTipAndTotalTextViews() {
        val tipView: TextView = binding.textView2
        tipView.text = "$tipPercent%"
        val totalView: TextView = binding.textView8
        totalView.text = "$${subtotal + (subtotal * tipPercent)/100}"
    }

    fun setUpRadioButtons() {
        val allButtons: List<View> = listOf(binding.radioButton, binding.radioButton2, binding.radioButton3, binding.radioButton4)
        for (button in allButtons) {
            button.setOnClickListener { view ->
                when (view) {
                    binding.radioButton -> tipPercent = 10
                    binding.radioButton2 -> tipPercent = 15
                    binding.radioButton3 -> tipPercent = 18
                    binding.radioButton4 -> tipPercent = 25
                }
                binding.seekBar.progress = tipPercent
                setTipAndTotalTextViews()
            }
        }
    }
    fun setUpTipSeekBar() {
        val tipSeekBar = binding.seekBar
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
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}