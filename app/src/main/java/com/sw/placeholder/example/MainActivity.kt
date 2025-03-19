package com.sw.placeholder.example

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sw.placeholder.common.MyAppTopBar
import com.sw.placeholder.example.ui.navigation.MainNavigation
import com.sw.placeholder.example.ui.theme.PlaceHolderCommentsExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaceHolderCommentsExampleTheme() {
                Scaffold(topBar = {
                    MyAppTopBar(
                        title = "List",
                        onClick = {},
                        isBackNavigation = false
                    )
                }) { innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        Box(modifier = Modifier.padding(innerPadding)) {
                            MainNavigation()
                        }
                    }
                }

            }
        }
    }
}