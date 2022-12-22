package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}


data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        val imageModifier = Modifier
            .size(25.dp)
            .clip(CircleShape) // 先に書いておかないと切り取れない
            .background(Color.Magenta)
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = imageModifier
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column() {
            Text(text = msg.author, fontSize = 10.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body, fontSize = 10.sp)
        }
    }
}

@Composable
private fun Greeting02(name: String) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(all = 10.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Hello,")
            Text(text = name)
        }
    }
}

@Composable
private fun Greeting03(name: String) {
    Surface(modifier = Modifier.padding(all = 5.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Gray)
        ) {
            Text(text = "Hello 02, $name", color = Color.LightGray)
        }
    }
}

@Preview(widthDp = 300, name = "MessageCardPreview")
@Composable
fun MessageCardPreview() {
    Column() {
        Row(modifier = Modifier.padding(top = 15.dp)) {
            Greeting02("Greeting02")
            MessageCard(
                msg = Message("題名：お天気は？", "本文：今日は雨ですね！")
            )
        }
        Greeting03(name = "MaxSize03")
    }
}


@Preview(widthDp = 150, name = "List Preview")
@Composable()
fun ListPreview() {
    var list = listOf("abc", "def", "ghi")

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (item in list) {
                Text("Item:  $item")
            }
        }
        Text("Count:  ${list.size}")

    }

}


@Preview(name = "Click Button Change color", widthDp = 250)
@Composable
fun ClickButtonChangeColor() {
    val expanded = remember { mutableStateOf(true) }

    val mod01 = Modifier
        .background(
            color = Color(0xFF12887D),
            shape = RoundedCornerShape(16.dp)
        )

    val mod02 = Modifier
        .background(
            color = Color.Blue,
            shape = RoundedCornerShape(16.dp)
        )

    ElevatedButton(
        onClick = { expanded.value = !expanded.value },

        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.White,
        ),
        modifier = if (expanded.value) mod01 else mod02,
    ) {
        Text(
            text = if (expanded.value) "壱" else "弐",
            color = if (expanded.value) Color.Black else Color.White
        )
    }
}

@Preview(name = "Text Disply")
@Composable
fun TextDisplyPreview() {
    Column(modifier = Modifier.padding(all = 8.dp)) {
        Text(
            text = stringResource(R.string.text_hello) + " 04\naaa",
            color = Color(0xFFE4DC98),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .background(
                    color = Color(0xFF000000),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(all = 8.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            "Hello\nAndroid!",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(
                    color = Color(0xFFFF0000),
                    shape = RoundedCornerShape(30.dp),
                )
                .padding(all = 8.dp)
        )
    }
}

data class Employee(
    var name: String,
    var id: String,
    var email: String,
    var sns: String,
    var phone: String,
    val photo: Int
)


@Composable
fun BusinessCard(employee: Employee) {
    val image = painterResource(id = R.drawable.ic_launcher_foreground)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Image(
            painter = painterResource(id = employee.photo),
            contentDescription = "ドロイド君",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = employee.name,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
        )
        Text(
            text = employee.id,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .padding(end = 50.dp),
        )
        Spacer(modifier = Modifier.padding(18.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            var padding: Int = 20
            var titleSpace = 60
            Row() {
                Text(text = "email", modifier = Modifier.width(titleSpace.dp))
                Spacer(modifier = Modifier.padding(start = padding.dp))
                Text(text = employee.email)
            }
            Row() {
                Text(text = "sns", modifier = Modifier.width(titleSpace.dp))
                Spacer(modifier = Modifier.padding(start = padding.dp))
                Text(text = employee.sns)
            }
            Row() {
                Text(text = "phone", modifier = Modifier.width(titleSpace.dp))
                Spacer(modifier = Modifier.padding(start = padding.dp))
                Text(text = employee.phone)
            }
        }
    }
}

@Preview
@Composable
fun businessCardPreview() {
    var employee: Employee =
        Employee(
            "Tom Cat",
            "12-3456",
            "abc@gmail.com",
            "@tech",
            "033-456-7890",
            R.drawable.ic_launcher_foreground
        )
    BusinessCard(employee)
}

