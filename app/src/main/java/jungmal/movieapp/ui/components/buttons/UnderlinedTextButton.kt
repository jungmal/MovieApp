package jungmal.movieapp.ui.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import jungmal.movieapp.ui.theme.Paddings
import jungmal.movieapp.ui.theme.currentColorScheme
import jungmal.movieapp.ui.theme.underlinedButton

@Composable
fun UnderlinedTextButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.currentColorScheme.background,
            contentColor = MaterialTheme.currentColorScheme.secondary,
            disabledContainerColor = MaterialTheme.currentColorScheme.background,
            disabledContentColor = MaterialTheme.currentColorScheme.onBackground
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = id?.let { stringResource(id = id) } ?: text,
                style = MaterialTheme.typography.underlinedButton,
                modifier = Modifier.padding(Paddings.small)
            )
        }
    }
}

@Preview
@Composable
fun UnderlinedTextButtonPreview() {
    MaterialTheme {
        UnderlinedTextButton(
            text = "Submit"
        ) {

        }
    }

}