package com.ferdi.restaurankotlin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ferdi.restaurankotlin.data.model.Menu
import com.ferdi.restaurankotlin.utils.addToCart
import com.ferdi.restaurankotlin.utils.formatRupiah
import com.ferdi.restaurankotlin.utils.isItemInCart
import com.ferdi.restaurankotlin.utils.updateQuantityCart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMenuScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    data: Menu
) {
    val context = LocalContext.current
    var quantity by remember { mutableIntStateOf(1) }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        modifier = Modifier
                            .testTag(stringResource(R.string.back_to_menu_btn)),
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
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
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    onClick = {
                        if(quantity>1) {
                            quantity--
                        }
                    }
                ) {
                    Icon(
                        Icons.Default.Remove,
                        contentDescription = "Icon minus"
                    )
                }
                Text(
                    quantity.toString(),
                    fontSize = 14.sp,
                )
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    onClick = {
                        quantity++
                    }
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Icon add"
                    )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .testTag(stringResource(R.string.insert_cart_btn))
                ,
                onClick = {
                    val result = if(!isItemInCart(data.title, context)) {
                        addToCart(
                            name = data.title,
                            quantity = quantity,
                            price = data.price,
                            image = data.image,
                            context = context
                        )
                    } else {
                        updateQuantityCart(data.title, quantity, context)
                    }
                    val resultSuccess = result > 0
                    if(resultSuccess) {
                        Toast.makeText(context, context.getString(R.string.add_to_cart_success_toast, quantity), Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, context.getString(R.string.add_to_cart_failed_toast), Toast.LENGTH_SHORT).show()
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
