package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CategoriesScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()) {
        searchBar()
    }
}

@Preview(showBackground = true)
@Composable
fun searchBar(){
    Row (modifier = Modifier.statusBarsPadding()){
        Icon(
            Icons.Sharp.ArrowBack,
            contentDescription = "Arrow back Icon",
            modifier = Modifier
                .padding(7.dp)

        )
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        {
            Icon (
                Icons.Sharp.Search,
                contentDescription = "Search Icon",
                modifier = Modifier
                    .padding(4.dp)
            )
        }
    }

}
/*
@Composable
fun CategoriesGrid(arrayPosts: Array<PostModel>){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp)

    ){
        items(arrayPosts){ post ->
            PostCard(post.id, post.title, post.text, post.image)
        }
    }
}
 */