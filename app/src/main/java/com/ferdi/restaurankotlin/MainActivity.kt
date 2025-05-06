package com.ferdi.restaurankotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ferdi.restaurankotlin.ui.theme.RestauranKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestauranKotlinTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = { FAB() }
                ) { innerPadding ->
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        verticalItemSpacing = 8.dp,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        items(20) {
                            ItemMenu(name = "Es Jeruk", price = 1500.0)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemMenu(modifier: Modifier = Modifier, name: String, price: Double) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .height(150.dp),
            painter = painterResource(R.drawable.es_teh),
            contentDescription = "Es Teh",
            contentScale = ContentScale.Fit
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                price.toString(),
                fontSize = 12.sp
            )

        }
    }
}

@Composable
fun FAB(modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = {  },
    ) {
        Icon(Icons.Filled.ShoppingCart, "Cart.")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RestauranKotlinTheme {
        ItemMenu(name = "Es Jeruk", price = 1500.0)
    }
}