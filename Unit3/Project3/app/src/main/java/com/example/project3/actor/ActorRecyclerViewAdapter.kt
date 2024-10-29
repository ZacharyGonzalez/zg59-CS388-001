package com.example.project3.actor

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project3.OnListFragmentInteractionListener
import com.example.project3.R

class ActorRecyclerViewAdapter(
    private val actors: List<Actor>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<ActorRecyclerViewAdapter.ActorViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_actor, parent, false)
        return ActorViewHolder(view)
    }


    inner class ActorViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Actor? = null
        val mActorTitle: TextView = mView.findViewById<View>(R.id.ActorBanner) as TextView
        val mOverview: TextView = mView.findViewById<View>(R.id.ActorOverview) as TextView
        val mActorImage: ImageView = mView.findViewById<View>(R.id.Actor_image) as ImageView
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        val prefaceURL= "https://image.tmdb.org/t/p/w500/" 
        holder.mItem = actor
        holder.mActorTitle.text = actor.title
        holder.mOverview.text = actor.overview

        // Check the orientation
        var image:String?=null
        val orientation = holder.mView.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            image = prefaceURL+actor.actorImageUrl
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            image = prefaceURL+actor.actorImageUrl
        }

        Glide.with(holder.mView)
            .load(image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerInside()
            .into(holder.mActorImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { actor ->
                mListener?.onItemClick(actor)
            }

        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return actors.size
    }
}