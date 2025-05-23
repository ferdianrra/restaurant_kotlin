package com.ferdi.restaurankotlin

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ferdi.restaurankotlin.data.database.CartHelper
import com.ferdi.restaurankotlin.data.model.Cart
import com.ferdi.restaurankotlin.utils.MappingHelper
import com.ferdi.restaurankotlin.utils.formatRupiah
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var cartList by remember { mutableStateOf<List<Cart>>(emptyList()) }

    LaunchedEffect(Unit) {
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
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f).testTag(stringResource(R.string.cart_list_tag)),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cartList.size) { index ->
                    val cart = cartList[index]
                    ItemCart(
                        title = cart.title,
                        price = cart.price,
                        image = cart.image
                    )
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onClick = {}
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
    image: Int
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
                formatRupiah(price),
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