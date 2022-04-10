package live.taskr.taskr.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import live.taskr.taskr.R
import live.taskr.taskr.ui.theme.blue700
import live.taskr.taskr.ui.theme.green500
import live.taskr.taskr.utils.models.HomeViewModel
import live.taskr.taskr.utils.models.TagTypes

@Composable
fun UpcomingTasks() {
    Column {
//        Tags()
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Meet ")
                }
                append("Jones Barry")
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_clock),
                contentDescription = ""
            )
            Text(text = "10:00")
            Icon(
                painter = painterResource(R.drawable.ic_calender),
                contentDescription = ""
            )
            Text(text = "Tomorrow")
        }
        Row(
//            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            // TODO: overlapping users icon
            Icon(
                painter = painterResource(R.drawable.ic_profile_logo),
                contentDescription = ""
            )
            Icon(
                painter = painterResource(R.drawable.ic_profile_logo),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun Tags(
    tag: List<TagTypes>
) {
    tag.forEach { tagTypes ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentWidth()
                .clip(shape = RoundedCornerShape(50.dp))
                .background(color = green500)
                .padding(top = 2.dp, bottom = 2.dp, start = 4.dp, end = 4.dp)
        ) {
            Text(
                text = when (tagTypes) {
                    TagTypes.Work -> stringResource(R.string.work_tag)
                    TagTypes.Personal -> stringResource(R.string.personal_tag)
                },
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
            )
        }
    }
}

@Preview()
@Composable
fun PreviewTag() {
    UpcomingTasks()
}
