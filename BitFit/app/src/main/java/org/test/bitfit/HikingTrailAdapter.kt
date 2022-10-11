package org.test.bitfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HikingTrailAdapter(private val context: Context, private val hikingTrails: List<HikingTrailEntity>) :
    RecyclerView.Adapter<HikingTrailAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val trailTextView = itemView.findViewById<TextView>(R.id.trailNameTv)
        private val milesTextView = itemView.findViewById<TextView>(R.id.milesTravelledTv)
        private val workoutTextView = itemView.findViewById<TextView>(R.id.workoutTimeTv)

        // grab values from database
        fun bind (hikingTrail: HikingTrailEntity){
            trailTextView.text = hikingTrail.trailName
            milesTextView.text = hikingTrail.milesTravelled
            workoutTextView.text = hikingTrail.workoutTime
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.model_hiking_trail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hikingTrail = hikingTrails[position]
        holder.bind(hikingTrail)
    }

    // return hiking trails size
    override fun getItemCount() = hikingTrails.size
}