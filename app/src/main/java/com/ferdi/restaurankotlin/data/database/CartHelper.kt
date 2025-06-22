package com.ferdi.restaurankotlin.data.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_ID
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_IMAGE
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_PRICE
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_QUANTITY
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.COLUMN_NAME_TITLE
import com.ferdi.restaurankotlin.data.database.DatabaseContract.RestaurantDatabase.TABLE_NAME
import com.ferdi.restaurankotlin.data.model.Cart
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

    fun isItemInCart(name: String): Boolean {
        val cursor = database.query(
            DATABASE_TABLE,
            arrayOf(COLUMN_NAME_ID),
            "$COLUMN_NAME_TITLE = ?",
            arrayOf(name),
            null, null, null
        )

        val exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }

    fun updateById(id: Int, values: ContentValues): Int {
        return database.update(
            DATABASE_TABLE,
            values,
            "$COLUMN_NAME_ID = ?",
            arrayOf(id.toString())
        )
    }

    fun getCartItemByName(name: String): Cart? {
        val cursor = database.query(
            DATABASE_TABLE,
            arrayOf(COLUMN_NAME_ID, COLUMN_NAME_TITLE, COLUMN_NAME_IMAGE, COLUMN_NAME_PRICE, COLUMN_NAME_QUANTITY),
            "$COLUMN_NAME_TITLE = ?",
            arrayOf(name),
            null, null, null
        )

        val item = if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ID))
            val quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY))
            val price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NAME_PRICE))
            val image = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_IMAGE))
            Cart(id, name, image, price, quantity)
        } else null

        cursor.close()
        return item
    }


    fun deleteAll(): Int {
        return database.delete(DATABASE_TABLE, null, null)
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