package com.ferdi.restaurankotlin.data.database

import android.provider.BaseColumns

object DatabaseContract {
    object RestaurantDatabase : BaseColumns {
        const val TABLE_NAME = "cart"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_IMAGE = "image"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_QUANTITY = "quantity"
    }
}