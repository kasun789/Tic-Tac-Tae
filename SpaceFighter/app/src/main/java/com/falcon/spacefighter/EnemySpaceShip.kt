import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.falcon.spacefighter.R
import kotlin.random.Random

class EnemySpaceShip(context: Context) {
    var context: Context = context
    var enemySpaceShip: Bitmap
    var enemyXAxis: Int = 0 // Assuming default values for these properties
    var enemyYAxis: Int = 0
    var enemyVelocity: Int = 0
    var random: Random = Random

    init {
        enemySpaceShip = BitmapFactory.decodeResource(context.resources, R.drawable.enemyspaceship)
    }

    fun retrieveEnemySpaceShip(): Bitmap {
        return enemySpaceShip;
    }

    fun getEnemySpaceShipWidth(): Int{
        return enemySpaceShip.width;
    }

    fun getEnemySpaceShipHeight(): Int{
        return enemySpaceShip.height;
    }

    private fun resetEnemySpaceShip(){
        enemyXAxis = 200 + random.nextInt();
        enemyYAxis = 0;
        enemyVelocity = 14 + random.nextInt(10);
    }
}


