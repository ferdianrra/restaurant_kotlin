package com.ferdi.restaurankotlin.utils

import android.content.ContentValues
import android.content.Context
import com.ferdi.restaurankotlin.data.database.CartHelper
import com.ferdi.restaurankotlin.data.database.DatabaseContract
import java.text.NumberFormat
import java.util.Locale

fun formatRupiah(number: Double): String {
    val localeID = Locale("in", "ID")
    val formatter = NumberFormat.getCurrencyInstance(localeID)
    return formatter.format(number)
}

fun addToCart(
    name: String,
    price: Double,
    image: Int,
    quantity: Int,
    context: Context
): Long {
    val cartHelper = CartHelper.getInstance(context)
    cartHelper.open()
    val values = ContentValues()
    values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_TITLE, name)
    values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_PRICE, price)
    values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_IMAGE, image)
    values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_QUANTITY, quantity)
    val result = cartHelper.insert(values)
    cartHelper.close()
    return result
}

fun updateQuantityCart(name: String, quantity: Int, context: Context): Long {
    val cartHelper = CartHelper.getInstance(context)
    cartHelper.open()
    var result = 0
    val cart = cartHelper.getCartItemByName(name)
    if(cart != null) {
        val values = ContentValues().apply {
            put("quantity", quantity+cart.quantity)
        }
        result = cartHelper.updateById(cart.id, values)
    }
    cartHelper.close()
    return result.toLong()
}

fun isItemInCart(name: String, context: Context): Boolean {
    val cartHelper = CartHelper.getInstance(context)
    cartHelper.open()
    val result = cartHelper.isItemInCart(name)
    cartHelper.close()
    return result
}