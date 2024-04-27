package com.falcon.spacefighter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Shot(context: Context, shotXAxis: Int, shotYAxis: Int) {
    var shot: Bitmap;
    var context:Context = context;
    var shX: Int;
    var shY: Int;

    init {
        shot = BitmapFactory.decodeResource(context.resources,R.drawable.bullet);
        shX = shotXAxis;
        shY = shotYAxis;
    }

    fun getShots(): Bitmap{
        return shot;
    }
}