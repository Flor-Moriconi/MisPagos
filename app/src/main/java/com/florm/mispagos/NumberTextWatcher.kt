package com.florm.mispagos

import android.text.TextWatcher
import android.text.Editable
import android.widget.EditText
import java.text.DecimalFormat
import java.text.ParseException

class NumberTextWatcher(editText: EditText, pattern: String) : TextWatcher {

    private var df: DecimalFormat = DecimalFormat(pattern)
    private var dfnd: DecimalFormat
    private var et: EditText? = null
    private var hasFractionalPart: Boolean = false

    init {
        df.isDecimalSeparatorAlwaysShown = true
        dfnd = DecimalFormat("#,###")
        et = editText
        hasFractionalPart = false
    }

    override fun afterTextChanged(s: Editable) {
        et?.removeTextChangedListener(this)

        try {
            var inilen = 0
            var endlen = 0

            et?.text?.length?.let {
                inilen = it
            }

            val v = s.toString().replace((df.decimalFormatSymbols.groupingSeparator).toString(), "")
            val n = df.parse(v)

            var cp = 0

            et?.selectionStart?.let {
                cp = it
            }

            if (hasFractionalPart) {
                et?.setText(df.format(n))
            } else {
                et?.setText(dfnd.format(n))
            }

            et?.text?.length?.let {
                endlen = it
            }

            val sel = cp + (endlen - inilen)

            var editTextLength = 0
            et?.text?.length?.let {
                editTextLength = it
            }

            if (sel in 1..editTextLength) {
                et?.setSelection(sel)
            } else {
                // place cursor at the end?
                et?.setSelection(editTextLength - 1)
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        et?.addTextChangedListener(this)
    }


    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        hasFractionalPart = s.toString().contains((df.decimalFormatSymbols.decimalSeparator).toString())
    }

}