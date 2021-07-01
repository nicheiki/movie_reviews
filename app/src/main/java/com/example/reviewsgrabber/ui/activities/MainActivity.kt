package com.example.reviewsgrabber.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commitNow
import androidx.lifecycle.lifecycleScope
import com.example.reviewsgrabber.R
import com.example.reviewsgrabber.ui.fragments.MovieListFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            lifecycleScope.launch {
                supportFragmentManager.commitNow {
                    setReorderingAllowed(true)
                    delay(3000)
                    add<MovieListFragment>(R.id.fragment_container_view)
                }
            }
        }
    }
}