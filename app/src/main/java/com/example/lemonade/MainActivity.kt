package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFF9E44C)
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                1 -> LemonStep(
                    textRes = R.string.limon1,
                    imageRes = R.drawable.lemon_tree,
                    contentDesc = R.string.lemon_tree_content_description,
                    onClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
                2 -> LemonStep(
                    textRes = R.string.limon2,
                    imageRes = R.drawable.lemon_squeeze,
                    contentDesc = R.string.lemon_content_description,
                    onClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
                3 -> LemonStep(
                    textRes = R.string.limon3,
                    imageRes = R.drawable.lemon_drink,
                    contentDesc = R.string.lemon_drink_content_description,
                    onClick = { currentStep = 4 }
                )
                4 -> LemonStep(
                    textRes = R.string.limon4,
                    imageRes = R.drawable.lemon_restart,
                    contentDesc = R.string.lemon_restart_content_description,
                    onClick = { currentStep = 1 }
                )
            }
        }
    }
}

@Composable
fun LemonStep(
    textRes: Int,
    imageRes: Int,
    contentDesc: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC3ECD2) // tu color personalizado
            )
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = stringResource(contentDesc),
                modifier = Modifier
                    .width(dimensionResource(R.dimen.button_image_width))
                    .height(dimensionResource(R.dimen.button_image_height))
                    .padding(dimensionResource(R.dimen.button_interior_padding))
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(textRes))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}

