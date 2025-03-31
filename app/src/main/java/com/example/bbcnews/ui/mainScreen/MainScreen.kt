package com.example.bbcnews.ui.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.bbcnews.R
import com.example.bbcnews.ui.newsScreen.NewsScreenViewModel
import com.example.bbcnews.ui.newsScreen.NewsUiState
import model.News
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val newsScreenViewModel: NewsScreenViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as FragmentActivity
    )
    val mainScreenViewModel: MainScreenViewModel = koinViewModel()
    val uiState by mainScreenViewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.height(70.dp),
                title = { Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "BBC News") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (uiState) {
                    is NewsUiState.Loading -> CircularProgressIndicator()

                    is NewsUiState.Success -> {
                        val news = (uiState as NewsUiState.Success).news
                        NewsList(news, navController, newsScreenViewModel)
                    }

                    is NewsUiState.Error -> Text(text = "Error: ${(uiState as NewsUiState.Error).message}")
                }
            }
        }
    }
}

@Composable
fun NewsList(news: News, navController: NavController, newsScreenViewModel: NewsScreenViewModel) {
    for (i in 0..< news.articles.size) {
        Card(
            modifier = Modifier
                .height(100.dp)
                .padding(bottom = 7.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { newsScreenViewModel.assignArticle(news.articles[i]); navController.navigate("NewsScreen") }
        ) {
            Row {
                Column(modifier = Modifier.width(100.dp)) {
                    AsyncImage(
                        model = news.articles[i].urlToImage,
                        contentDescription = null,
                        modifier = Modifier.fillMaxHeight(),
                        contentScale = ContentScale.Crop,
                        fallback = painterResource(id = R.drawable.unavailable),
                        error = painterResource(id = R.drawable.unavailable)
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    text = if(news.articles[i].title.length > 0)
                              news.articles[i].title
                           else
                              "Title not available."
                )
            }
        }
    }
}