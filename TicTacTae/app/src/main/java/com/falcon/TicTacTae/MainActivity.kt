package com.falcon.spacefighter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Arrays

class MainActivity : AppCompatActivity() {
    var gameActive = true
    var activePlayer = 0
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    // State meanings:
    // 0 - X
    // 1 - O
    // 2 - Null
    val winPositions = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
    )

    var counter = 0

    fun playerTap(view: View) {
        val img = view as ImageView
        val tappedImage = img.tag.toString().toInt()

        if (!gameActive) {
            gameReset(view)
            counter = 0
        }

        if (gameState[tappedImage] == 2) {
            counter++
            if (counter == 9) {
                gameActive = false
            }
            gameState[tappedImage] = activePlayer
            img.translationY = -1000f

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x)
                activePlayer = 1
                findViewById<TextView>(R.id.status).text = "O's Turn - Tap to play"
            } else {
                img.setImageResource(R.drawable.o)
                activePlayer = 0
                findViewById<TextView>(R.id.status).text = "X's Turn - Tap to play"
            }
            img.animate().translationYBy(1000f).setDuration(300)
        }

        var flag = 0
        if (counter > 4) {
            for (winPosition in winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2
                ) {
                    flag = 1
                    val winnerStr = if (gameState[winPosition[0]] == 0) "X" else "O"
                    gameActive = false
                    val dbHelper = DbHelper(this);
                    var currentScore = dbHelper.findByname(this,winnerStr);
                    if(currentScore != null){
                        dbHelper.updateData(this,winnerStr,currentScore.toInt()+10);
                    }else{
                        Log.d("print", "playerTap: ")
                        dbHelper.insertData(winnerStr,10);
                    }

                    val intent = Intent(this,GameResult::class.java);
                    intent.putExtra("result",winnerStr);
                    startActivity(intent);
                }
            }
            if (counter == 9 && flag == 0) {
//                findViewById<TextView>(R.id.status).text = "Match Draw"
                val intent = Intent(this,GameResult::class.java);
                intent.putExtra("result","Match Draw");
                startActivity(intent);
            }
        }
    }

    fun gameReset(view: View) {
        gameActive = true
        activePlayer = 0
        Arrays.fill(gameState, 2)
        val imageIds = intArrayOf(
            R.id.imageView0, R.id.imageView1, R.id.imageView2,
            R.id.imageView3, R.id.imageView4, R.id.imageView5,
            R.id.imageView6, R.id.imageView7, R.id.imageView8
        )
        for (id in imageIds) {
            (findViewById<View>(id) as ImageView).setImageResource(0)
        }
        findViewById<TextView>(R.id.status).text = "X's Turn - Tap to play"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}