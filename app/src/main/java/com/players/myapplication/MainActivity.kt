package com.players.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {
    private val builder : StringBuilder = StringBuilder();
    private val results : StringBuilder = StringBuilder();
    private fun cal(str : String) : Double?{
        val engine = ScriptEngineManager().getEngineByExtension("js")!!
        try {
            return engine.eval(str) as Double?
        }catch(e : Exception){
            return null
        }
    }

    private fun append(obj : Any?){
        results.clear()
        if(builder.toString().contains("ERROR"))
            builder.clear()
        builder.append(obj)
        findViewById<TextView>(R.id.operator).setText(builder.toString())

        results.append(cal(builder.toString())?:"ERROR")
        findViewById<EditText>(R.id.result).setText(results.toString())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button0).setOnClickListener { append(0) }
        findViewById<Button>(R.id.button1).setOnClickListener { append(1) }
        findViewById<Button>(R.id.button2).setOnClickListener { append(2) }
        findViewById<Button>(R.id.button3).setOnClickListener { append(3) }
        findViewById<Button>(R.id.button4).setOnClickListener { append(4) }
        findViewById<Button>(R.id.button5).setOnClickListener { append(5) }
        findViewById<Button>(R.id.button6).setOnClickListener { append(6) }
        findViewById<Button>(R.id.button7).setOnClickListener { append(7) }
        findViewById<Button>(R.id.button8).setOnClickListener { append(8) }
        findViewById<Button>(R.id.button9).setOnClickListener { append(9) }
        //findViewById<Button>(R.id.buttonClear).setOnClickListener { builder.clear() }
        findViewById<Button>(R.id.buttonComma).setOnClickListener { append(".")}
        findViewById<Button>(R.id.buttonPlus).setOnClickListener { append ("+")}
        findViewById<Button>(R.id.buttonMin).setOnClickListener { append("-") }
        findViewById<Button>(R.id.buttonMult).setOnClickListener { append("*")}
        findViewById<Button>(R.id.buttonDiv).setOnClickListener { append("/")}
        findViewById<Button>(R.id.clearLast).setOnClickListener {
            if(builder.isNotEmpty()){
                builder.deleteCharAt(builder.length-1)
                findViewById<TextView>(R.id.operator).setText(builder.toString())
                append("")
            }
        }
        findViewById<Button>(R.id.buttonResult).setOnClickListener {
            builder.clear();
            builder.append(results.toString())
            findViewById<TextView>(R.id.operator).setText(builder.toString())
            results.clear()
        }
    }
}
