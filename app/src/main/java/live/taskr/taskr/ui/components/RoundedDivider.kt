package live.taskr.taskr.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import live.taskr.taskr.ui.theme.blueGrey600

@Composable
fun RoundedDivider(modifier: Modifier) {
    Divider(
        color = blueGrey600,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .height(8.dp)
            .wrapContentWidth()
            .width(3.dp)
            .clip(shape = RoundedCornerShape(10.dp))
    )
}