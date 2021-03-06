package live.taskr.taskr.ui.navComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.taskr.taskr.R
import live.taskr.taskr.ui.theme.TaskrTheme

@Composable
fun TabButton(
    title: String,
    contentDescription: String,
    icon: Painter,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Row(
       modifier =  Modifier.wrapContentWidth()
    ) {
        val colors = MaterialTheme.colors
        val imageAlpha = if (isSelected) 1f else 0.5f
        val backgroundColor = if (isSelected) colors.secondary else Color.Transparent
        val iconTint = if (isSelected) Color.White else colors.onPrimary
//        val buttonModifier = Modifier.padding()
        Surface(

            color = backgroundColor,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(8.dp).wrapContentWidth(),
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 16.dp, end = 16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(0.dp)
                ) {
                    Image(
                        painter = icon,
                        contentDescription = contentDescription,
                        colorFilter = ColorFilter.tint(iconTint))
//                    Spacer(Modifier.width(8.dp))
                    Text(text = title)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNavButton() {
    TaskrTheme {
        TabButton(
            title = "Tasks",
            icon = painterResource(R.drawable.ic_tasks),
            onClick = {},
            isSelected = true,
            contentDescription = ""
        )
    }
}