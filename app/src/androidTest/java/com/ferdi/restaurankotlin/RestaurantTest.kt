package com.ferdi.restaurankotlin

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTouchInput
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ferdi.restaurankotlin.data.model.dummyMenu
import kotlinx.coroutines.delay
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
        for (index in 0 until dummyMenu.size) {
            val title = dummyMenu[index].title
            composeTestRule.onNodeWithTag(
                composeTestRule.activity.getString(R.string.menu_list_tag)
            ).performScrollToIndex(index)
            composeTestRule.onNodeWithTag("add-to-cart-$title").performScrollTo()
            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithTag("add-$title").performClick()
            composeTestRule.onNodeWithTag("add-$title").performClick()

            composeTestRule.onNodeWithTag("add-to-cart-$title").performClick()
            Thread.sleep(2000)
        }
    }

    @Test
    fun scrolling_menu() {
        val startTime = System.currentTimeMillis()
        val duration = 30_000L // 30 detik
        val menuList = dummyMenu
        while (System.currentTimeMillis() - startTime < duration) {
            // Scroll ke bawah
            for(index in 0 until menuList.size) {
                composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.menu_list_tag)).performScrollToIndex(index)
            }

            // Scroll ke atas
            for(index in menuList.size-1 until 0) {
                composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.menu_list_tag)).performScrollToIndex(index)
            }
        }
    }

    @Test
    fun navigate_to_detail_menu() {
        Thread.sleep(1000)
        for (index in 0 until 30) {
            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.detail_btn)+ dummyMenu[0].title).performScrollTo()
            composeTestRule.waitForIdle()
            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.detail_btn)+dummyMenu[0].title).performClick()
            composeTestRule.onNodeWithText(dummyMenu[0].title).assertIsDisplayed()
            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.back_to_menu_btn)).performClick()
            composeTestRule.onNodeWithText("Menu").assertIsDisplayed()

        }
        Thread.sleep(1000)
 //       Thread.sleep(5000)
    }
}