package com.scode.elementtransitiontask_composeapp.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scode.elementtransitiontask_composeapp.R
import com.scode.elementtransitiontask_composeapp.screens.model.ItemData

//9227411113
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Int, String,String) -> Unit
) {


    val itemsProduct = listOf(
        ItemData(R.drawable.food_1, "Chow mein","A dish is a specific food preparation that is ready to eat or serve."),
        ItemData(R.drawable.food_2, "Palak paneer","A dish is a specific food preparation that is ready to eat or serve."),
        ItemData(R.drawable.food_3, "Punjabi Karhi","A dish is a specific food preparation that is ready to eat or serve."),
        ItemData(R.drawable.food_4, "Tandoori chicken","A dish is a specific food preparation that is ready to eat or serve."),
        ItemData(R.drawable.food_5, "Punjabi Spacial","A dish is a specific food preparation that is ready to eat or serve."),
        ItemData(R.drawable.food_6, "Dan dan noodles","A dish is a specific food preparation that is ready to eat or serve."),
        ItemData(R.drawable.food_7, "Punjabi Naan","A dish is a specific food preparation that is ready to eat or serve."),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(itemsProduct) { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item.foodImage, item.text, item.desc) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = item.foodImage),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(12 / 9f)
                        .weight(1f)
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/${item.foodImage}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ).clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = buildAnnotatedString {
                        append(item.text)
                        append("\n") // Line break between item.text and item.desc
                        withStyle(style = SpanStyle(fontSize = 14.sp)) {
                            append(item.desc) // item.desc with smaller font size
                        }
                    },
                    fontSize = 21.sp, // Default font size for item.text
                    modifier = Modifier
                        .weight(1f)
                        .sharedElement(
                            state = rememberSharedContentState(key = "text/${item.text}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        )
                )
            }
        }

    }

}