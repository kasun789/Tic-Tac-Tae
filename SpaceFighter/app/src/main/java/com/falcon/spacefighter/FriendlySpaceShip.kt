package com.falcon.spacefighter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlin.random.Random

class FriendlySpaceShip(context: Context ) {
    var context: Context = context
    var friendlySpaceShip: Bitmap
    var friendlyXAxis: Int = 0 // Assuming default values for these properties
    var friendlyYAxis: Int = 0
    var friendlyVelocity: Int = 0
    var random: Random = Random

    init {
        friendlySpaceShip = BitmapFactory.decodeResource(context.resources, R.drawable.enemyspaceship);
        resetFriendlySpaceShip();
    }

    fun retrieveFriendlySpaceShip(): Bitmap {
        return friendlySpaceShip;
    }

    fun getFriendlySpaceShipWidth(): Int{
        return friendlySpaceShip.width;
    }

    fun getFriendlySpaceShipHeight(): Int{
        return friendlySpaceShip.height;
    }

    private fun resetFriendlySpaceShip(){
        var spaceShooter = SpaceShooter(context);
        friendlyXAxis = random.nextInt(spaceShooter.screenWidth);
        friendlyYAxis = spaceShooter.screenWidth - friendlySpaceShip.height;
        friendlyVelocity = 10 + random.nextInt(6);
    }
}