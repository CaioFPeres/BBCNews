package com.example.bbcnews.ui.newsScreen

import android.content.Intent
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import androidx.core.net.toUri

@Composable
fun NewsScreen(navController: NavHostController, viewModel: NewsScreenViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val config = LocalConfiguration.current
    val context = LocalContext.current
    var width = if( config.screenWidthDp < config.screenHeightDp) config.screenWidthDp else config.screenHeightDp


    Scaffold() { paddingValues ->
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
                            text = uiState!!.title,
                            fontSize = 22.sp
                        )

                        Spacer(modifier = Modifier.padding(top = 12.dp))

                        Text(
                            text = uiState!!.description,
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
                            text = uiState!!.content,
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