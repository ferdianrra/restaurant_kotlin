package com.ferdi.restaurankotlin

import android.content.ContentValues
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import com.ferdi.restaurankotlin.data.database.CartHelper
import com.ferdi.restaurankotlin.data.database.DatabaseContract
import com.ferdi.restaurankotlin.data.model.dummyMenu
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CartTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        val dbHelper = CartHelper.getInstance(composeTestRule.activity)
        dbHelper.open()
        for(index in 0 until 100) {
            val values = ContentValues()
            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_TITLE, dummyMenu[0].title)
            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_PRICE, dummyMenu[0].price)
            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_IMAGE, dummyMenu[0].image)
            dbHelper.insert(values)
        }

        dbHelper.close()
    }

    @Test
    fun insertMenuToCart() {
        // Masukkan data ke database
        val dbHelper = CartHelper.getInstance(composeTestRule.activity)
        dbHelper.open()
        for (index in 0 until 100) {
            val values = ContentValues()
            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_TITLE, dummyMenu[0].title)
            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_PRICE, dummyMenu[0].price)
            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_IMAGE, dummyMenu[0].image)
            dbHelper.insert(values)  // Menyimpan data ke database
        }

        dbHelper.close()

        // Setelah data dimasukkan, lanjutkan tes untuk memastikan navigasi ke keranjang berfungsi dengan baik
  //      navigateToCart()  // Ini opsional di sini karena tes pertama hanya menambah item, navigasi ke keranjang bisa dilakukan di tes kedua
    }


    @Test
    fun load_database() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.navigate_to_cart_btn)).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.cart_list_tag)).performScrollToIndex(99)
        composeTestRule.waitForIdle()
    }
}