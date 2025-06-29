package com.ferdi.restaurankotlin

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ferdi.restaurankotlin.data.model.dummyMenu
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestaurantTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

//    @Before
//    fun setUp() {
//        composeTestRule.setContent {
//            RestauranKotlinTheme {
//                RestaurantApp()
//            }
//        }
//    }

    @Test
    fun insert_menu_to_cart() {
        for (index in 0 until 10) {
            // Mau menampilkan sesuai item
            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.detail_btn)+ dummyMenu[0].title).performScrollTo()
            composeTestRule.waitForIdle()
            // Menekan button detail yang ada di item
            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.detail_btn)+dummyMenu[0].title).performClick()
            // berpindah ke navigasi, memastikan navigasi sukses
            composeTestRule.onNodeWithText(dummyMenu[0].title).assertIsDisplayed()
            // Ensure the "Add to Cart" button is visible before clicking it
        //    composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.insert_cart_btn)).assertIsDisplayed()
            // menekan button add to cart di navigasi ke 2 (seharusnya kalo berhasil bakalamn ke back ke ome)
            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.insert_cart_btn)).performClick()
            // memastikan kalo udah bisa ke back
            composeTestRule.onNodeWithText("Menu").assertIsDisplayed()

        }
    }

    @Test
    fun navigate_to_detail_menu() {
        Thread.sleep(3000)
        for (index in 0 until 1) {
            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.detail_btn)+ dummyMenu[0].title).performScrollTo()
            composeTestRule.waitForIdle()
            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.detail_btn)+dummyMenu[0].title).performClick()
            composeTestRule.onNodeWithText(dummyMenu[0].title).assertIsDisplayed()
            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.back_to_menu_btn)).performClick()
            composeTestRule.onNodeWithText("Menu").assertIsDisplayed()

        }
        Thread.sleep(5000)
    }
}