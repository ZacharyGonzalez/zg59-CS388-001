package com.example.project3

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.project3.actor.ActorFragment
import com.example.project3.movie.MovieFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Can house our Fragments
        val supportFragmentManager = supportFragmentManager
        supportFragmentManager.beginTransaction()
            .add(R.id.MovieContent, MovieFragment())
            .add(R.id.ActorContent, ActorFragment())
            .commit()
    }
}