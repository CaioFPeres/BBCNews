package com.example.bbcnews.ui.newsScreen

import android.content.Intent
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import com.example.bbcnews.R
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen() {
    val newsScreenViewModel: NewsScreenViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as FragmentActivity
    )
    val uiState by newsScreenViewModel.uiState.collectAsState()
    val config = LocalConfiguration.current
    val context = LocalContext.current
    var width = if( config.screenWidthDp < config.screenHeightDp)
                    config.screenWidthDp
                else
                    config.screenHeightDp

    Log.d("DEBUG", uiState!!.url)
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(30.dp),
                title = {
                }
            )
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(modifier = Modifier
                .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = uiState!!.urlToImage,
                        contentDescription = null,
                        modifier = Modifier
                            .width(width.dp)
                            .padding(
                                top = if(config.orientation != ORIENTATION_LANDSCAPE)
                                          0.dp
                                      else
                                          15.dp
                            ) ,
                        fallback = painterResource(id = R.drawable.unavailable),
                        error = painterResource(id = R.drawable.unavailable)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 14.dp, top = 26.dp, end = 14.dp, bottom = 14.dp)
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column() {
                        Text(
                            fontSize = 22.sp,
                            text = if(uiState!!.title.length > 0)
                                        uiState!!.title
                                   else
                                        "Title not available."
                        )

                        Spacer(modifier = Modifier.padding(top = 12.dp))

                        Text(
                            text = if(uiState!!.description.length > 0)
                                        uiState!!.description
                                   else
                                        "Description not available.",
                            fontSize = 10.sp,
                            color = Color.White,
                            textAlign = TextAlign.End
                        )

                        HorizontalDivider(
                            color = Color.Gray,
                            modifier = Modifier.padding(
                                start = 10.dp,
                                top = 10.dp,
                                end = 10.dp,
                                bottom = 15.dp
                            )
                        )

                        Text(
                            text = if(uiState!!.content.length > 0)
                                       uiState!!.content
                                   else
                                       "Content not available.",
                            fontSize = 14.sp,
                            color = Color.White,
                            textAlign = TextAlign.Start
                        )

                        Text(
                            text = uiState!!.url,
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_VIEW, uiState!!.url.toUri())
                                context.startActivity(intent)
                            },
                            style = androidx.compose.ui.text.TextStyle(
                                color = Color(0xFFADD8E6),
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    }
                }
            }
        }
    }
}