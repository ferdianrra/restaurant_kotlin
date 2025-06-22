package com.ferdi.restaurankotlin.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_ID
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_IMAGE
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_PRICE
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_TITLE
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_QUANTITY
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.TABLE_NAME

internal class DatabaseHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_CART)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
       db.execSQL(SQL_DELETE_TABLE_CART)
        onCreate(db)
    }



    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "restaurant"
        private const val SQL_CREATE_TABLE_CART =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NAME_TITLE TEXT," +
                    "$COLUMN_NAME_IMAGE INTEGER," +
                    "$COLUMN_NAME_PRICE REAL," +
                    "$COLUMN_NAME_QUANTITY REAL)"

        private const val SQL_DELETE_TABLE_CART = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}