package com.ferdi.restaurankotlin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ferdi.restaurankotlin.data.model.dummyMenu
import com.ferdi.restaurankotlin.ui.theme.RestauranKotlinTheme
import com.ferdi.restaurankotlin.utils.formatRupiah

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    navigateToCart: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Menu")
                }
            )
        },
        floatingActionButton = {
            FAB(
                navigateToCart = navigateToCart
            )
        }
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(dummyMenu.size) { index ->
                ItemMenu(
                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    ,
                    name = dummyMenu[index].title,
                    price = dummyMenu[index].price,
                    imageMenu = dummyMenu[index].image,
                    onClick = { navigateToDetail(index) }
                )
            }
        }
    }
}

@Composable
fun ItemMenu(
    modifier: Modifier = Modifier,
    name: String,
    price: Double,
    imageMenu: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            painter = painterResource(imageMenu),
            contentDescription = "Image Menu",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                name,
                modifier = Modifier.testTag(name),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                formatRupiah(price),
                fontSize = 14.sp
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .testTag(stringResource(R.string.detail_btn)+name)
            ,
            onClick = onClick
        ) {
            Text(
                stringResource(R.string.detail_btn),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun FAB(
    modifier: Modifier = Modifier,
    navigateToCart: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier.testTag(stringResource(R.string.navigate_to_cart_btn)),
        onClick = navigateToCart,
    ) {
        Icon(Icons.Filled.ShoppingCart, "Cart.")
    }
}

@Preview(showBackground = true)
@Composable
fun ItemMenuPreview() {
    RestauranKotlinTheme {
        ItemMenu(
            name = "Es Jeruk",
            price = 1500.0,
            imageMenu = R.drawable.es_teh,
            onClick = {}
        )
    }
}