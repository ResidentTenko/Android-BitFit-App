package org.test.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.test.bitfit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val hikingTrails = mutableListOf<HikingTrailEntity>()
    private lateinit var hikingRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        hikingRecyclerView = findViewById(R.id.trailRv)
        val hikingAdapter = HikingTrailAdapter(this, hikingTrails)
        hikingRecyclerView.adapter = hikingAdapter

        lifecycleScope.launch {
            (application as HikingTrailApplication).db.hikingTrailDao().getAll().collect{ databaseList ->
                databaseList.map { entity ->
                    HikingTrailEntity(
                        entity.trailName,
                        entity.milesTravelled,
                        entity.workoutTime,
                    )
                }.also { mappedList ->
                    hikingTrails.clear()
                    hikingTrails.addAll(mappedList)
                    hikingAdapter.notifyDataSetChanged()
                }
            }
        }
        hikingRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            hikingRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        // go to TrailDetailActivity on click
        val newTrailButton = findViewById<Button>(R.id.addTrail)
        newTrailButton.setOnClickListener{
            val intent = Intent(this, TrailDetailActivity::class.java)
            startActivity(intent)
        }

    }

}