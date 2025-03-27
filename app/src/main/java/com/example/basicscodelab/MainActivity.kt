package com.example.basicscodelab

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    companion object {
        val TAG: String = MainActivity.javaClass.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { innerPadding ->
                MainUI(modifier = Modifier.fillMaxSize().padding(innerPadding))
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, expand: Boolean = false) {
    var expanded by remember { mutableStateOf(expand) }
    val extraPadding = if (expanded) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row (modifier = modifier.padding(24.dp)) {
            Column(modifier = modifier
                .weight(.1f)
                .padding(bottom = extraPadding)){
                Text(text = "Hello")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded = !expanded },
            ) {
                Text(if (expanded) "Show Less" else "Show More")
            }
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "Item $it" }
) {
    Surface (
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn (modifier= modifier.padding(vertical = 4.dp)) {
            items(names) { name ->
                Greeting(name = name, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun MainUI(modifier: Modifier = Modifier) {
    BasicsCodelabTheme {
        MyApp(modifier)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyApp(modifier: Modifier = Modifier, shouldShowOnBoarding: Boolean = true) {
    //hoisted state
    var shouldShowOnBoarding by remember { mutableStateOf(shouldShowOnBoarding) }
    val onContinueClicked = { shouldShowOnBoarding = false }

    Surface(modifier) {
        if (shouldShowOnBoarding) OnBoardingScreen(onContinueClicked = onContinueClicked) else Greetings()
    }
}

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Welcome to Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) { Text("Continue") }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun MainUIPreview() {
    MainUI(modifier = Modifier.fillMaxSize())
}

@Preview(showSystemUi = true)
@Composable
fun MyAppPreview() {
    Scaffold { innerPadding ->
        BasicsCodelabTheme {
            MyApp(modifier = Modifier.fillMaxSize().padding(innerPadding), shouldShowOnBoarding = false)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun GreetingPreview() {
    Greeting(
        modifier = Modifier,
        name = "test1",
        expand = true
    )
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {
        OnBoardingScreen(onContinueClicked = {})
    }
}