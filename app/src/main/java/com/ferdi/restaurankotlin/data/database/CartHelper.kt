package com.ferdi.restaurankotlin.data.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_ID
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.TABLE_NAME
import java.sql.SQLException
import kotlin.jvm.Throws

class CartHelper(context: Context) {

    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if(database.isOpen) {
            database.close()
        }
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_NAME_ID ASC",
            null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: CartHelper? = null
        fun getInstance(context: Context): CartHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: CartHelper(context)
            }
    }
}