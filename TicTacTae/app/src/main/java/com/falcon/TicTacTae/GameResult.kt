package com.falcon.spacefighter

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameResult : AppCompatActivity(){
    val dbHelper = DbHelper(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        var winingPlaye = findViewById<TextView>(R.id.winingPlayer);
        if(intent.getStringExtra("result") == "Match Draw") {
            winingPlaye.text = intent.getStringExtra("result");
        }else {
            winingPlaye.text = intent.getStringExtra("result") + " Has Won";
        }
    }

    fun reStartGame(view: View){
        var intent = Intent(this,MainActivity::class.java);
        startActivity(intent);
    }

    fun showPointsTable(view: View){
        val builder = AlertDialog.Builder(this);
        val dbHelper = DbHelper(this);
        val data = dbHelper.getData();

//        dbHelper.getMaxValue(this);

        // Set the title and message for the dialog
        builder.setTitle("Highest Scores")
        val messageBuilder = StringBuilder()

        if (data != null) {
            for ((playerName, score) in data) {
                messageBuilder.append("Player $playerName: $score\n")
            }
        }
        builder.setMessage(messageBuilder.toString())

        // Set the positive button and its click listener
        builder.setPositiveButton("OK") { dialog, _ ->
            // Do something when the "OK" button is clicked
            dialog.dismiss() // Dismiss the dialog
        }

        // Create and show the AlertDialog
        val dialog = builder.create()
        dialog.show()
    }
}