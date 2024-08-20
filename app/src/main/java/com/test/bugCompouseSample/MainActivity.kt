package com.test.bugCompouseSample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.composable
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.test.bugCompouseSample.ui.theme.BugCompouseSampleTheme

internal const val SCREEN_ONE_NAVIGATION_LINK = "screen_one/"
internal const val SCREEN_TWO_NAVIGATION_LINK = "screen_two/"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BugCompouseSampleTheme {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()

                Scaffold(
                ) { innerPadding ->
                    SampleNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SampleNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_ONE_NAVIGATION_LINK,
    ) {
        composable(SCREEN_ONE_NAVIGATION_LINK) {
            ScreenOne(
                onButtonClick = {
                    navController.navigate(SCREEN_TWO_NAVIGATION_LINK)
                },
            )
        }
        composable(SCREEN_TWO_NAVIGATION_LINK) {
            ScreenTwo(
                onButtonClick = {
                    navController.navigateUp()
                },
            )
        }
    }
}

@Composable
fun ScreenOne(
    onButtonClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier.weight(0.3f),
                onClick = {
                    onButtonClick.invoke()
                },
                content = {
                    Text(text = "Click me")
                }
            )
            val context = LocalContext.current
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(30.dp)
                    .clickable {
                        Toast.makeText(context, "Page 1 clicked", Toast.LENGTH_SHORT).show()
                    }
                    .wrapContentHeight(align = Alignment.CenterVertically)
                ,
                text = "Page 1",
            )
        }
    }
}

@Composable
fun ScreenTwo(
    onButtonClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier.weight(0.3f),
                onClick = {
                    onButtonClick.invoke()
                },
                content = {
                    Text(text = "Click me")
                }
            )
            val context = LocalContext.current
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(30.dp)
                    .clickable {
                        Toast.makeText(context, "Page 2 clicked", Toast.LENGTH_SHORT).show()
                    }
                    .wrapContentHeight(align = Alignment.CenterVertically)

                ,
                text = "Page 2",
            )
        }
    }
}
