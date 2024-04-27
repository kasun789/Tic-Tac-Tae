package com.falcon.spacefighter

import EnemySpaceShip
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.view.Display
import android.view.View
import java.util.logging.Handler
import kotlin.random.Random

class SpaceShooter(context: Context): View(context) {
    var con: Context = context;
    var background: Bitmap;
    var lifeImage: Bitmap;
//    var handler: Handler;
    var UPDATE_MILLIS: Long = 30;
    var screenWidth: Int;
    var screenHeight: Int;
    var points: Int = 0;
    var life: Int = 3;
    var scorePaint: Paint;
    var TEXT_SIZE: Float = 80.0F;
    var paused = false;
    var friendlySpaceship: FriendlySpaceShip;
    var enemySpaceship: EnemySpaceShip;
    var random: Random;
    var enemyShots = arrayListOf<Shot>();
    var friendlyShots = arrayListOf<Shot>();
    var enemyExplosion = false;
//    var explosion: Explosion;
    var explosions = arrayListOf<Explosion>();
    var enemyShotAction = false;

    init {
        random = Random(10);
        val display: Display = (context as Activity).windowManager.defaultDisplay
        var size: Point = Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        friendlySpaceship = FriendlySpaceShip(context);
        enemySpaceship = EnemySpaceShip(context);
        background = BitmapFactory.decodeResource(context.resources, R.drawable.background);
        lifeImage = BitmapFactory.decodeResource(context.resources, R.drawable.life);
        scorePaint = Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.textSize = TEXT_SIZE;
        scorePaint.textAlign = Paint.Align.LEFT;
//        explosion = Explosion(context,)

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(background,0F,0F, null);
        canvas.drawText("Pt:"+ points,0F,TEXT_SIZE,scorePaint)

    }

}