package com.example.tapcounter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var i = 0
        var x = 1
        var y = 10
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            findViewById<Button>(R.id.b1).setOnClickListener{_->
                i+=x
                findViewById<TextView>(R.id.textView).setText("$i")
                if (i>=10*x) {
                    findViewById<TextView>(R.id.b2).visibility = View.VISIBLE
                }
            }

            findViewById<Button>(R.id.b2).setOnClickListener{_->
                if (i>=10*x){
                    i-=y
                    x++
                    y = 10*x
                    findViewById<TextView>(R.id.textView).setText("$i")
                    findViewById<TextView>(R.id.b2).setText("Upgrade Cost: $y")
                    Toast.makeText(applicationContext, "Taps worth $x", Toast.LENGTH_SHORT).show()
                    findViewById<TextView>(R.id.b2).visibility = View.INVISIBLE
                }
                else{
                    //redundant failsafe for upgrade button
                    Toast.makeText(applicationContext, "Not enough Points", Toast.LENGTH_SHORT).show()
                }
            }
            insets
        }
    }
}