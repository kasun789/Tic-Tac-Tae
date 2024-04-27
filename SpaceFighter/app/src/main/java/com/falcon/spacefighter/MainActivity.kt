package com.falcon.spacefighter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge();
        setContentView(R.layout.activity_main);
    }
    fun startGame(view: View){
        Log.d("startup","hello");
//        setContentView(R.layout.space_shooter)
        val intent = Intent(this, StartUp::class.java);
        startActivity(intent);
        finish();
    }
}