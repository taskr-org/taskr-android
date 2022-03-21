package live.taskr.taskr.ui.theme

import androidx.compose.foundation.background
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

private val DarkColorPalette = darkColors(
    primary = blueGrey900,
    onPrimary = blueGrey100,
    primaryVariant = blueGrey700,
    secondary = blueGrey500,
    onSecondary = blueGrey100,
    secondaryVariant = grey500,
    background = blueGrey900,
    onBackground = blueGrey100,
    error = red200,
    onError = blueGrey100,
    surface = blueGrey900,
    onSurface = blueGrey100
)

@Composable
fun TaskrTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}