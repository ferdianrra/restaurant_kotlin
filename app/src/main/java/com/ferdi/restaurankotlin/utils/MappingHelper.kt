package com.ferdi.restaurankotlin.utils

import android.database.Cursor
import com.ferdi.restaurankotlin.data.database.DatabaseContract
import com.ferdi.restaurankotlin.data.model.Cart

object MappingHelper {
    fun mapCursorToArrayList(cartCursor: Cursor?): ArrayList<Cart> {
        val cartList = ArrayList<Cart>()
        cartCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.RestaurantDatabase.COLUMN_NAME_ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.RestaurantDatabase.COLUMN_NAME_TITLE))
                val price = getDouble(getColumnIndexOrThrow(DatabaseContract.RestaurantDatabase.COLUMN_NAME_PRICE))
                val image = getInt(getColumnIndexOrThrow(DatabaseContract.RestaurantDatabase.COLUMN_NAME_IMAGE))
                val quantity = getInt(getColumnIndexOrThrow(DatabaseContract.RestaurantDatabase.COLUMN_NAME_QUANTITY))
                cartList.add(Cart(id, title, image, price, quantity))
            }
        }
        return cartList
    }
}