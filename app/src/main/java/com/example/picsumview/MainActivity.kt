package com.example.picsumview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.picsumview.ui.PicsumFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PicsumFragment.newInstance())
                .commitNow()
        }
    }
}