package com.falcon.spacefighter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Explosion(context: Context, explosionX: Int, explosionY: Int) {
    var explosion = arrayOfNulls<Bitmap>(2);
    var explosionFrame: Int;
    var expX: Int;
    var expY: Int;

    init {
        explosion[0] = BitmapFactory.decodeResource(context.resources, R.drawable.blast02);
        explosion[1] = BitmapFactory.decodeResource(context.resources, R.drawable.blast01);
        explosionFrame = 0;
        expX = explosionX;
        expY = explosionY
    }

    fun getExplosion(explosionFrame: Int): Bitmap?{
        return explosion[explosionFrame];
    }
}