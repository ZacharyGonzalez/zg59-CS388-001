package com.example.project3

import com.example.project3.actor.Actor
import com.example.project3.movie.Movie

interface OnListFragmentInteractionListener {
    fun getCurrentItem(): Any?
    fun onItemClick(movie: Movie)
    fun onItemClick(actor: Actor)
}

