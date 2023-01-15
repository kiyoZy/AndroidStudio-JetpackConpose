package com.example.timer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.timer.ui.theme.TimerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime

/*

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timer.ui.theme.TimerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerTheme {
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

/*
//@Preview
@Composable
fun LaunchedEffectSampleToggle() {
    val context = LocalContext.current
    var state by remember { mutableStateOf(false) }

    if (state) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, "start $state", Toast.LENGTH_SHORT).show()
            delay(5000)
            Toast.makeText(context, "end $state", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = state.toString(),
//                style = MaterialTheme.typography.h1,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(onClick = { state = true }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "TRUE")
            }
            Button(onClick = { state = false }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "FALSE")
            }
        }
    }
}

 */

// https://stackoverflow.com/questions/74235464/jetpack-compose-make-launchedeffect-keep-running-while-app-is-running-in-the-b

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TestTimer02() {
    var time: Int by remember { mutableStateOf(0) }
    var timerSwitch by remember { mutableStateOf(false) }
    var dateAndtime: LocalTime = LocalTime.now()

    LaunchedEffect(key1 = true, block = {

        CoroutineScope(Dispatchers.IO).launch {

            // 6秒で終わりにする設定
            while (time < 6) {

                // スイッチ判定
                if (timerSwitch) {

                    // 1秒に1回来たメモ
                    time = time + 1
                }
                dateAndtime = LocalTime.now()

                // 待ち時間　1000ミリ秒 = 1秒
                delay(1000)
            }
        }
    })

    Column() {

        // 回数・タイマー表示部分
        Column() {
            Text(if (timerSwitch) "tap: True" else "tap: False")
            Text("$time")
            Text("${dateAndtime.minute}:${dateAndtime.second}")
        }

        // ボタン表示部分
        Row() {
            Button(onClick = { timerSwitch = true }) {
                Text("Start")
            }
            Button(onClick = { timerSwitch = false }) {
                Text("Stop")
            }
        }
    }
}

/*
//@Preview(showBackground = true)
@Composable
fun TestTimer() {
    // https://koko206.hatenablog.com/entry/2022/02/02/000528


    var deadLine by remember { mutableStateOf(10000) }
    var cTime by remember { mutableStateOf(10000) }

    LaunchedEffect(key1 = deadLine, key2 = cTime) {
        if (cTime > 0) {
            cTime -= 1000
        }
    }

    Column() {
        Text("$cTime")
        Row() {
            Button(onClick = { /*TODO*/ }) {
                Text("Start")
            }
            Button(onClick = { /*TODO*/ }) {
                Text("Stop")
            }
        }
    }
}

 */