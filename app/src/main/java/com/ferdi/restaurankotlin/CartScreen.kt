package com.ferdi.restaurankotlin

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.ferdi.restaurankotlin.data.database.CartHelper
import com.ferdi.restaurankotlin.data.model.Cart
import com.ferdi.restaurankotlin.data.model.dummyMenu
import com.ferdi.restaurankotlin.utils.MappingHelper
import com.ferdi.restaurankotlin.utils.TestHooks
import com.ferdi.restaurankotlin.utils.formatRupiah
import com.ferdi.restaurankotlin.widget.AnimatedLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen(
    navController: NavHostController,
    navigateToCheckout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var cartList by remember { mutableStateOf<List<Cart>>(emptyList()) }
    var showAnimationSuccess by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullToRefreshState()

    fun onRefresh() {
        isRefreshing = true
        Log.d("Trigger","Is Refreshing: $isRefreshing")
        cartList = listOf()
        Log.d("Trigger","Cart List Size: ${cartList.size}")
        isRefreshing = false
    }

    LaunchedEffect(Unit) {
        // Load pertama kali
        cartList = withContext(Dispatchers.IO) {
            loadCart(context)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text("Cart")
                },
                actions = {
                    IconButton(
                        modifier = Modifier.testTag(stringResource(R.string.refresh_cart_tag)),
                        onClick = {
                            onRefresh()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            if(cartList.isNotEmpty()) {
                PullToRefreshBox(
                    modifier = Modifier
                        .weight(1f),
                    //    .testTag(stringResource(R.string.refresh_cart_tag)),
                    state = pullRefreshState,
                    isRefreshing = isRefreshing,
                    onRefresh =  {
                        runBlocking {
                            TestHooks.refreshTrigger?.invoke()
                        }
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag(stringResource(R.string.cart_list_tag)),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(cartList.size) { index ->
                            val cart = cartList[index]
                            ItemCart(
                                title = cart.title,
                                price = cart.price,
                                image = cart.image,
                                quantity = cart.quantity
                            )
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.empty_cart))
                }
            }

//            if(showAnimationSuccess) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .zIndex(1f)
//                        .testTag("success_animation"),
//                    contentAlignment = Alignment.Center
//                ) {
//                    AnimatedLoader(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(200.dp)
//                            .padding(top = 16.dp)
//                    )
//                }
//            }
            Button(
                modifier = Modifier
                    .testTag(stringResource(R.string.checkout_tag))
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onClick = {
                    coroutineScope.launch {
                        val cartHelper = CartHelper.getInstance(context)
                        cartHelper.open()
                        val result = cartHelper.deleteAll()
                        cartHelper.close()
                        Log.d("result","result: $result")
                        if (result > 0) {
//                            cartList = withContext(Dispatchers.IO) { loadCart(context) }
//                            showAnimationSuccess = true
//                            Log.d("animation","Animation Success: $showAnimationSuccess")
//                            delay(5000L)
//                            showAnimationSuccess = false
//                            Log.d("animation","Animation Success: $showAnimationSuccess")
                            navigateToCheckout()
                        }
                    }

                }
            ) {
                Text(
                    "Check Out",
                    fontSize = 14.sp
                )
            }
        }
    }
}


@Composable
private fun ItemCart(
    modifier: Modifier = Modifier,
    title: String,
    price: Double,
    image: Int,
    quantity: Int
) {
    Row(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(16.dp)),
            painter = painterResource(image),
            contentDescription = "Image Menu",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(
                title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Quantity: $quantity",
                fontSize = 14.sp
            )
            Text(
                "Price: ${formatRupiah(price)}",
                fontSize = 14.sp
            )
        }
    }
}

private fun loadCart(
    context: Context
) : List<Cart> {
    val cartHelper = CartHelper.getInstance(context)
    cartHelper.open()
    val cursor = cartHelper.queryAll()
    val result = MappingHelper.mapCursorToArrayList(cursor)
    cartHelper.close()
    return result
}


@Preview(showBackground = true)
@Composable
private fun ItemCartPreview() {
    ItemCart(
        title = "Judul 1",
        price = 10000.0,
        image = dummyMenu[0].image,
        quantity = 10

    )
}