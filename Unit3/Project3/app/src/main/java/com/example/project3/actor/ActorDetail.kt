package com.example.project3.actor

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.project3.R

private const val TAG = "DetailActivity"
const val ACTOR_EXTRA = "ACTOR_EXTRA"


class ActorDetail : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var KnownForTextView: TextView
    private lateinit var abstractTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        KnownForTextView = findViewById(R.id.mediaByline)
        abstractTextView = findViewById(R.id.mediaAbstract)
        // TODO: Get the extra from the Intent
        val actor = intent.getSerializableExtra(ACTOR_EXTRA) as Actor

        // TODO: Set the title, KnownFor, and abstract information from the article
        titleTextView.text = actor.title
        titleTextView.append(", ")
        titleTextView.append(actor.original_name)
        KnownForTextView.text="Known for: "
        abstractTextView.text = "Has a rating popularity of: "
        abstractTextView.append(actor.popularity.toString())

        for (movie in actor.knownFor){
            KnownForTextView.append("\n${movie.title}\n")
        }
        // TODO: Load the media image
// Load actor image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + actor.actorImageUrl)
            .into(mediaImageView)
    }

}