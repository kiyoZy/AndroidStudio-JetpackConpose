package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun LemonApp() {

    // Current step the app is displaying (remember allows the state to be retained
    // across recompositions).
    var currentStep by remember { mutableStateOf(1) }

    // Number of times the lemon needs to be squeezed to turn into a glass of lemonade
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                // Display lemon tree image and ask user to pick a lemon from the tree
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_select,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lemon_tree_content_description,
                    onImageClick = {
                        // Update to next step
                        currentStep = 2
                        // Each time a lemon is picked from the tree, get a new random number
                        // between 2 and 4 (inclusive) for the number of times the lemon needs
                        // to be squeezed to turn into lemonade
                        squeezeCount = (1..2).random()
                    }
                )
            }

            2 -> {
                // Display lemon image and ask user to squeeze the lemon
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_squeeze,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon_content_description,
                    onImageClick = {
                        // Decrease the squeeze count by 1 for each click the user performs
                        squeezeCount--
                        // When we're done squeezing the lemon, move to the next step
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }

            3 -> {
                // Display glass of lemonade image and ask user to drink the lemonade
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_drink,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.lemonade_content_description,
                    onImageClick = {
                        // Update to next step
                        currentStep = 4
                    }
                )
            }

            4 -> {
                // Display empty glass image and ask user to start again
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_empty_glass,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.empty_glass_content_description,
                    onImageClick = {
                        // Back to starting step
                        currentStep = 1
                    }
                )
            }
        }
    }
}

/**
 * Composable that displays a text label above an image that is clickable.
 *
 * @param textLabelResourceId is the resource ID for the text string to display
 * @param drawableResourceId is the resource ID for the image drawable to display below the text
 * @param contentDescriptionResourceId is the resource ID for the string to use as the content
 * description for the image
 * @param onImageClick will be called when the user clicks the image
 * @param modifier modifiers to set to this composable
 */
@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textLabelResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun LemonPreview() {
    LemonadeTheme() {
        LemonApp()
    }
}


data class TextAndImage(
    var lemonText: Int = 0,
    var lemonImage: Int = 0,
    var lemonDirection: Int = 0
)

@Preview(name = "02")
@Composable
fun LemonApp02() {
    var currentStep by remember { mutableStateOf(1) }
    var textAndImage = TextAndImage()

    when (currentStep) {
        1 -> textAndImage = TextAndImage(
            R.string.lemon_select,
            R.drawable.lemon_tree,
            R.string.lemon_tree_content_description
        )

        2 -> textAndImage = TextAndImage(
            R.string.lemon_squeeze,
            R.drawable.lemon_squeeze,
            R.string.lemon_content_description
        )

        3 -> textAndImage = TextAndImage(
            R.string.lemon_drink,
            R.drawable.lemon_drink,
            R.string.lemonade_content_description
        )

        4 -> textAndImage = TextAndImage(
            R.string.lemon_empty_glass,
            R.drawable.lemon_restart,
            R.string.empty_glass_content_description
        )
    }

    Column(
        modifier = Modifier
    ) {
        Text(
            text = stringResource(textAndImage.lemonText),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Image(
            painter = painterResource(textAndImage.lemonImage),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .clickable(
                    onClick = {
                        when (currentStep) {
                            1 -> currentStep = 2
                            2 -> currentStep = 3
                            3 -> currentStep = 4
                            4 -> currentStep = 1
                        }
                    }
                )
                .border(
                    BorderStroke(2.dp, Color.Magenta),
                    shape = RoundedCornerShape(50.dp)
                )
        )
    }
}


@Preview(name = "03")
@Composable
fun LemonApp03() {
    var currentStep by remember { mutableStateOf(1) }
    var textAndImage = TextAndImage()

    when (currentStep) {
        1 -> {
            textAndImage = TextAndImage(
                R.string.lemon_select,
                R.drawable.lemon_tree,
                R.string.lemon_tree_content_description
            )
            LemonTextAndmage03(textAndImage, onImageClick = { currentStep = 2 })
        }

        2 -> {
            textAndImage = TextAndImage(
                R.string.lemon_squeeze,
                R.drawable.lemon_squeeze,
                R.string.lemon_content_description
            )
            LemonTextAndmage03(textAndImage, onImageClick = { currentStep = 3 })
        }

        3 -> {
            textAndImage = TextAndImage(
                R.string.lemon_drink,
                R.drawable.lemon_drink,
                R.string.lemonade_content_description
            )
            LemonTextAndmage03(textAndImage, onImageClick = { currentStep = 4 })
        }

        4 -> {
            textAndImage = TextAndImage(
                R.string.lemon_empty_glass,
                R.drawable.lemon_restart,
                R.string.empty_glass_content_description
            )
            LemonTextAndmage03(textAndImage, onImageClick = { currentStep = 1 })
        }
    }
}

@Composable
fun LemonTextAndmage03(
    textAndImage: TextAndImage,
    onImageClick: () -> Unit,
) {
    Column(modifier = Modifier) {
        Text(
            text = stringResource(textAndImage.lemonText),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Image(
            painter = painterResource(textAndImage.lemonImage),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color.Magenta),
                    shape = RoundedCornerShape(50.dp)
                )
        )
    }
}
