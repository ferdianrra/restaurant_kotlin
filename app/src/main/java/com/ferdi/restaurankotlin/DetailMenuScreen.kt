package com.ferdi.restaurankotlin

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import com.ferdi.restaurankotlin.data.database.DatabaseContract
import com.ferdi.restaurankotlin.data.model.Menu
import com.ferdi.restaurankotlin.utils.formatRupiah

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMenuScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    data: Menu
) {
    val context = LocalContext.current
    Scaffold(
        modifier = modifier.fillMaxSize(),
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
                    Text("Menu")
                }
            )
        },
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column (
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ){
            Image(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                    .height(200.dp),
                painter = painterResource(data.image),
                contentDescription = "Image Menu",
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    data.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    formatRupiah(data.price),
                    fontSize = 18.sp
                )
            }
            Text(
                data.description,
                modifier = Modifier.weight(1f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .testTag(stringResource(R.string.insert_cart_btn))
                ,
                onClick = {
                    val result = addToCart(
                        name = data.title,
                        price = data.price,
                        image = data.image,
                        context = context
                    )
                    val resultSuccess = result > 0
                    if (resultSuccess) {
                        navController.popBackStack()
                    } else {
                        Toast.makeText(
                            context,
                            "Failed to add data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Text(
                    stringResource(R.string.insert_cart_btn),
                    fontSize = 14.sp
                )
            }
        }
    }
}

private fun addToCart(
     name: String,
     price: Double,
     image: Int,
     context: Context
): Long {
    val cartHelper = CartHelper.getInstance(context)
    cartHelper.open()
    val values = ContentValues()
    values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_TITLE, name)
    values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_PRICE, price)
    values.put(DatabaseContract.RestaurantDatabase.COLUMN_NAME_IMAGE, image)

    val result = cartHelper.insert(values)
    cartHelper.close()
    return result
}