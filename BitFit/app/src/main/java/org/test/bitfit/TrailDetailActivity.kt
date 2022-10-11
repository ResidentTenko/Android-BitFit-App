package org.test.bitfit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TrailDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trail_detail)

        /************************* HELPER FUNCTIONS *************************/
        // hide keyboard on enter
        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }
        /************************ END HELPER FUNCTIONS ********************/

        // make a button
        val saveTrailBtn = findViewById<Button>(R.id.saveNewTrailBt)
        /**
         * create EditText objects and connect them to the xml
         * Use them to grab values entered by the user
         */

        saveTrailBtn.setOnClickListener{
            // hide the button on button click
            var trail = findViewById<EditText>(R.id.trailInputEt).text.toString()
            var miles = findViewById<EditText>(R.id.milesTravelledInputEt).text.toString()
            var time = findViewById<EditText>(R.id.workoutTimeInputEt).text.toString()

            it.hideKeyboard()
            // change all values to String since our database only takes string

            /**
             * Step 5: Populate our database with input values
             * Dispatchers.IO - This dispatcher is optimized to perform disk or network I/O
             * outside of the main thread. Examples include using the Room component,
             * reading from or writing to files, and running any network operations.
             */
            lifecycleScope.launch(IO) {
                // get an instance of our specific database and get the dao
                (application as HikingTrailApplication).db.hikingTrailDao().insert(
                    // now create a table instance and feed it with the input values
                    // then plug that into the database
                    HikingTrailEntity(trail, "$miles miles walked", "$time hours spent")
                )
            }
            // return back to main activity
            finish()
        }
    }
}