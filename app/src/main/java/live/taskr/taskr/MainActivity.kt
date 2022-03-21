package live.taskr.taskr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import live.taskr.taskr.screens.LoginScreen
import live.taskr.taskr.ui.navComponents.TaskrBottomNavBar
import live.taskr.taskr.ui.theme.TaskrTheme
import live.taskr.taskr.ui.theme.blueGrey900

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskrTheme {
                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = blueGrey900
                    )
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen()

//                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {

   Scaffold(
       bottomBar = {
           TaskrBottomNavBar(isSelected = true, currentRoute = "xyz")
       }) {
   }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskrTheme {
        LoginScreen()
    }
}