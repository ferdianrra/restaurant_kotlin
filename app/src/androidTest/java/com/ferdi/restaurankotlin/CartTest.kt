package com.ferdi.restaurankotlin

import android.R.attr.duration
import android.R.attr.endY
import android.R.attr.startY
import android.content.ContentValues
import android.util.Log.i
import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performGesture
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipe
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.test.swipeWithVelocity
import com.ferdi.restaurankotlin.data.database.CartHelper
import com.ferdi.restaurankotlin.data.database.DatabaseContract
import com.ferdi.restaurankotlin.data.model.dummyMenu
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.nio.file.Files.size

import androidx.compose.ui.unit.IntSize
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.action.ViewActions.swipeDown
import com.ferdi.restaurankotlin.data.model.Cart
import com.ferdi.restaurankotlin.utils.TestHooks
import com.ferdi.restaurankotlin.utils.inRealTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class CartTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    @Before
    fun setUp() {
        val dbHelper = CartHelper.getInstance(composeTestRule.activity)
        dbHelper.open()
        for(index in 0 until 100) {
            val values = ContentValues()
            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_TITLE, dummyMenu[index%11].title)
            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_PRICE, dummyMenu[index%11].price)
            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_IMAGE, dummyMenu[index%11].image)
            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_QUANTITY, 3)
            dbHelper.insert(values)
        }

        dbHelper.close()
    }

//    @Test
//    fun insertMenuToCart() {
//        // Masukkan data ke database
//        val dbHelper = CartHelper.getInstance(composeTestRule.activity)
//        dbHelper.open()
//        for (index in 0 until 100) {
//            val values = ContentValues()
//            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_TITLE, dummyMenu[0].title)
//            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_PRICE, dummyMenu[0].price)
//            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_IMAGE, dummyMenu[0].image)
//            values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_QUANTITY, 3)
//            dbHelper.insert(values)  // Menyimpan data ke database
//        }
//
//        dbHelper.close()
//
//        // Setelah data dimasukkan, lanjutkan tes untuk memastikan navigasi ke keranjang berfungsi dengan baik
//  //      navigateToCart()  // Ini opsional di sini karena tes pertama hanya menambah item, navigasi ke keranjang bisa dilakukan di tes kedua
//    }


//    @Test
//    fun load_database() {
//        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.navigate_to_cart_btn)).performClick()
//        composeTestRule.waitForIdle()
//        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.cart_list_tag)).performScrollToIndex(20)
//        composeTestRule.waitForIdle()
//    }

//    @Test
//    fun simulateUserLikeScrolling() {
//        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.navigate_to_cart_btn)).performClick()
//        composeTestRule.waitForIdle()
//
//        val scrollSteps = 20  // Berapa banyak langkah scroll
//        val delayPerScroll = 200L  // 200ms per langkah (sesuai user biasanya 100–300ms)
//
//        for (i in 0..scrollSteps) {
//            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.cart_list_tag))
//                .performScrollToIndex(i)
//
//            composeTestRule.waitForIdle()
//            Thread.sleep(delayPerScroll)
//        }
//
//        // Lanjut scroll balik ke atas (simulasi user yang eksplor)
//        for (i in scrollSteps downTo 0) {
//            composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.cart_list_tag))
//                .performScrollToIndex(i)
//
//            composeTestRule.waitForIdle()
//            Thread.sleep(delayPerScroll)
//        }
//    }

    @Test
    fun refresh_cart() {
        val startTime = System.currentTimeMillis()
        val duration = 30_000L

        // Navigasi ke halaman cart
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.navigate_to_cart_btn))
            .performClick()
        composeTestRule.waitForIdle()

        // Ambil ukuran node "Cart Refresh"
        val semanticsNode = composeTestRule.onNodeWithTag("Cart Refresh").fetchSemanticsNode()
        val width = semanticsNode.boundsInRoot.width
        val height = semanticsNode.boundsInRoot.height

        // Hitung titik swipe


        // Lakukan gesture swipe ke bawah
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.refresh_cart_tag))
            .performTouchInput {
                val centerX = width / 2f
                val startY = height * 0.2f
                val endY = height * 1f

                down(Offset(centerX, startY))
                moveTo(Offset(centerX, (startY + endY) / 2f))
                moveTo(Offset(centerX, endY))
                up() // <--- PENTING


            }

        Thread.sleep(5000)
    }

    @Test
    fun trigger_refresh_cart() {
        val startTime = System.currentTimeMillis()
        val duration = 30_000L

        // Navigasi ke halaman cart
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.navigate_to_cart_btn))
            .performClick()
        composeTestRule.waitForIdle()
        Thread.sleep(5000)
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.refresh_cart_tag)).performClick()
        Thread.sleep(5000)
    }

//    @Test
//    fun simulate_checkout_cart() {
//        val startTime = System.currentTimeMillis()
//        val duration = 30_000L
//
//        // Navigasi ke halaman cart
//        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.navigate_to_cart_btn))
//            .performClick()
//        composeTestRule.waitForIdle()
//        Thread.sleep(2000)
//        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.checkout_tag))
//            .performClick()
//        composeTestRule.mainClock.autoAdvance = true // ini defaultnya true
//
//// Tunggu sampai animasi tampil (kalo sempat muncul)
//        composeTestRule.waitUntil(timeoutMillis = 1000L) {
//            composeTestRule.onAllNodesWithTag("success_animation").fetchSemanticsNodes().isNotEmpty()
//        }
//
//// Tambahan: beri waktu supaya animation bisa muncul secara visual
//        composeTestRule.waitForIdle()
//        composeTestRule.mainClock.advanceTimeBy(500L) // memaksa recomposition
//
//        composeTestRule.onNodeWithTag("success_animation").assertExists()
//     //   Thread.sleep(10000)
//    }

    @Test
    fun simulate_checkout_cart() {
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.navigate_to_cart_btn)
        ).performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.checkout_tag)
        ).performClick()

        composeTestRule.inRealTime("checkout-animation", duration = 30_000L) {
            // Biarkan animasi jalan real-time selama 10 detik
        }

    //    composeTestRule.onNodeWithTag("success_animation").assertExists()
    }



    @Test
    fun simulateNaturalScroll() {
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.navigate_to_cart_btn)
        ).performClick()

        composeTestRule.waitForIdle()

        val listTag = composeTestRule.activity.getString(R.string.cart_list_tag)
        val scrollNode = composeTestRule.onNodeWithTag(listTag)

        val nodeHeight = scrollNode.fetchSemanticsNode().size.height.toFloat()
        val centerX = scrollNode.fetchSemanticsNode().boundsInRoot.center.x

        var scrollingDown = true
        val startTime = System.currentTimeMillis()
        val duration = 30_000L

        var lastTopBounds = scrollNode.fetchSemanticsNode().boundsInRoot.top
        var stuckCounter = 0

        while (System.currentTimeMillis() - startTime < duration) {
            scrollNode.performTouchInput {
                if (scrollingDown) {
                    swipe(
                        start = Offset(centerX, nodeHeight * 0.9f),
                        end = Offset(centerX, nodeHeight * 0f),
                        durationMillis = 1000
                    )
                } else {
                    swipe(
                        start = Offset(centerX, nodeHeight * 0.2f),
                        end = Offset(centerX, nodeHeight * 0.8f),
                        durationMillis = 600
                    )
                }
            }

            composeTestRule.waitForIdle()
            Thread.sleep(300)

            val currentTop = scrollNode.fetchSemanticsNode().boundsInRoot.top
            if (kotlin.math.abs(currentTop - lastTopBounds) < 5f) {
                stuckCounter++
            } else {
                stuckCounter = 0
            }

            lastTopBounds = currentTop

            if (stuckCounter >= 2) {
                scrollingDown = !scrollingDown
                stuckCounter = 0
            }
        }
    }




}