package com.example.basicscodelab

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
fun CardContent(name: String, expand: Boolean = false) {
    var expanded by rememberSaveable { mutableStateOf(expand) }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(modifier = Modifier
            .weight(.1f)
            .padding(12.dp)
        ){
            Text(text = "Hello")
            Text(text = name, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraLight))
            if (expanded) {
                Text(
                    text = ("""
                    Composem ipsum color sit lazy, padding theme elit, sed do bouncy.
                """.trimIndent()).repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
                contentDescription = if (expanded) stringResource(R.string.show_less) else stringResource(R.string.show_more)
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, expand: Boolean = false) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        CardContent(name, expand)
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
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(shouldShowOnBoarding) }
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
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light UI Mode"
)
@Composable
fun MainUIPreview() {
    Scaffold { innerPadding ->
        MainUI(modifier = Modifier.fillMaxSize().padding(innerPadding))
    }
}

@Preview(showSystemUi = true)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "MyApp Dark mode"
)
@Composable
fun MyAppPreview() {
    Scaffold { innerPadding ->
        BasicsCodelabTheme {
            MyApp(modifier = Modifier.fillMaxSize().padding(innerPadding), shouldShowOnBoarding = false)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        Greeting(
            modifier = Modifier.padding(),
            name = "test1",
            expand = true
        )
    }
}

