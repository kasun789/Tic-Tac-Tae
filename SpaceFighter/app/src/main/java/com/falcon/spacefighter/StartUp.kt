package com.falcon.spacefighter

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup
import kotlin.random.Random


class StartUp: AppCompatActivity() {
    private lateinit var handler: Handler;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge();
        setContentView(R.layout.space_shooter);

        handler = Handler();

        val friedlySpaceship = findViewById<ImageView>(R.id.friendly_ship);
        val point = findViewById<TextView>(R.id.points);
        val rootView = findViewById<View>(android.R.id.content);


        rootView.setOnTouchListener {_, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    friedlySpaceship.x = event.x - friedlySpaceship.width / 2;
                    true
                }

                else -> false
            }
        }

        handler.postDelayed({
            moveBullets();
        },20)
        calculatePoints(point)
    }


//    start the game
//    fun startGame(view: View){
//        Log.d("startup","hello");
//        val intent = Intent(this, MainActivity::class.java);
//        startActivity(intent);
//        finish();
//    }

    fun calculatePoints(textView: TextView){
        for(i in 1..10){
            textView.text = "Points : " + i;
        }
    }

    fun moveBullets(){
        val container = findViewById<View>(android.R.id.content) // Assuming you have a ViewGroup as a container

        // Generate and move ImageView objects
        val runnable = object : Runnable {
            override fun run() {
                // Generate ImageView
                Log.d("error", "runner working")
                val imageView = ImageView(this@StartUp)
                imageView.setImageResource(R.drawable.bullet) // Set your image resource here

                val layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // Add ImageView to the container
                findViewById<ViewGroup>(R.id.fragment_container).addView(imageView, layoutParams)

                // Move ImageView
                val random = Random(System.currentTimeMillis())
                val translationX = random.nextInt(container.width - imageView.width)
                val translationY = random.nextInt(container.height - imageView.height)
                imageView.translationX = translationX.toFloat()
                imageView.translationY = translationY.toFloat()

                // Schedule the next movement after some time delay
                handler.postDelayed(this, 20) // Delay of 2 seconds before moving next
            }
        }

        // Start the first movement
        handler.post(runnable)
    }
}