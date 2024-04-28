package com.falcon.spacefighter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

object TicTacToe{
    object Scores{
        const val TABLE_NAME = "Scores";
        const val COLUMN_ID = "_id";
        const val PLAYER_NAME = "player_name";
        const val SCORE = "player_score";
    }
}
class DbHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "mydatabase.db";
        const val DATABASE_VERSION = 1;
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Define the SQL statement to create the table
        val SQL_CREATE_ENTRIES = """
            CREATE TABLE ${TicTacToe.Scores.TABLE_NAME} (
                ${TicTacToe.Scores.COLUMN_ID} INTEGER PRIMARY KEY,
                ${TicTacToe.Scores.PLAYER_NAME} TEXT,
                ${TicTacToe.Scores.SCORE} INTEGER
            )
        """.trimIndent()

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades if needed
        // This example does nothing on upgrade
    }

    // Function to insert data into the database
    fun insertData(playerName: String,score: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(TicTacToe.Scores.PLAYER_NAME, playerName);
            put(TicTacToe.Scores.SCORE, score);
        }
        db.insert(TicTacToe.Scores.TABLE_NAME, null, values)
    }

    // Function to retrieve data from the database
    fun getData(): Map<String, Int>? {
        val db = readableDatabase
        val projection = arrayOf(TicTacToe.Scores.PLAYER_NAME,TicTacToe.Scores.SCORE)
        val cursor = db.query(
            TicTacToe.Scores.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        val result: MutableMap<String, Int> = mutableMapOf()

        with(cursor) {
            while (moveToNext()) {
                Log.d("print",getString(getColumnIndexOrThrow(TicTacToe.Scores.PLAYER_NAME) ));
//                result = getString(getColumnIndexOrThrow(TicTacToe.Scores.PLAYER_NAME)) + " -- " + getString(getColumnIndexOrThrow(TicTacToe.Scores.SCORE));
//                result.put(getString(getColumnIndexOrThrow(TicTacToe.Scores.PLAYER_NAME)),getInt(getColumnIndexOrThrow(TicTacToe.Scores.SCORE)))
                val playerName = getString(getColumnIndexOrThrow(TicTacToe.Scores.PLAYER_NAME))
                val score = getInt(getColumnIndexOrThrow(TicTacToe.Scores.SCORE))
                result[playerName] = score
            }
        }
        cursor.close()
        return result
    }

    fun truncateAllTables(context: Context) {
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase
        try {
            // Use DELETE statement without a WHERE clause to delete all rows from the tables
            db.execSQL("DELETE FROM ${TicTacToe.Scores.TABLE_NAME}")
            // Repeat this for each table in your database
        } finally {
            db.close()
        }
    }

    fun updateData(context: Context, playerName: String,score: Int) {
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase
        try {
            val values = ContentValues().apply {
                put(TicTacToe.Scores.SCORE, score)
            }
            // Specify the WHERE clause to update a specific row based on its ID
            val selection = "${TicTacToe.Scores.PLAYER_NAME} = ?"
            val selectionArgs = arrayOf(playerName)
            // Perform the update operation
            val count = db.update(
                TicTacToe.Scores.TABLE_NAME,
                values,
                selection,
                selectionArgs
            )
            // Optionally, handle the update count or perform error handling
        } finally {
            db.close()
        }
    }

    fun findByname(context: Context, name: String): String? {
        val dbHelper = DbHelper(context)
        val db = dbHelper.readableDatabase
        var result: String? = null

        try {
            val projection = arrayOf(TicTacToe.Scores.SCORE)
            val selection = "${TicTacToe.Scores.PLAYER_NAME} = ?"
            val selectionArgs = arrayOf(name)
            val cursor: Cursor = db.query(
                TicTacToe.Scores.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )
            var result: String? = null
            with(cursor) {
                while (moveToNext()) {
                    Log.d("print",getString(getColumnIndexOrThrow(TicTacToe.Scores.SCORE) ));
                    result = getString(getColumnIndexOrThrow(TicTacToe.Scores.SCORE));
                }
            }
            cursor.close()
            return result
        } finally {
            db.close()
        }
    }

    fun getMaxValue(context: Context): Pair<String, Int>? {
        val dbHelper = DbHelper(context)
        val db = dbHelper.readableDatabase
        var maxValue: Int? = null
        var maxPair: Pair<String, Int>? = null

        try {
            val column = TicTacToe.Scores.SCORE
            val projection = arrayOf(TicTacToe.Scores.PLAYER_NAME, "MAX($column)")
            val cursor: Cursor = db.query(
                TicTacToe.Scores.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )
            if (cursor.moveToFirst()) {
                Log.d("print", "getMaxValue: ")
                val playerName = cursor.getString(cursor.getColumnIndexOrThrow(TicTacToe.Scores.PLAYER_NAME))
                val score = cursor.getInt(cursor.getColumnIndexOrThrow(TicTacToe.Scores.SCORE))

                // Now you have the playerName and score
                // You can use them as needed
                Log.d("print", "Player Name: $playerName, Score: $score")

                // If you want to create a Pair with playerName and score
                val maxPair = Pair(playerName, score)
            }
        }
            finally {
                db.close()
            }
            return maxPair
    }
}