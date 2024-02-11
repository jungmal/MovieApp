package jungmal.movieapp.ui.components.dialog.components.title

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jungmal.movieapp.ui.components.buttons.PrimaryButton
import jungmal.movieapp.ui.components.buttons.SecondaryBorderlessButton
import jungmal.movieapp.ui.components.buttons.SecondaryButton
import jungmal.movieapp.ui.components.buttons.UnderlinedTextButton
import jungmal.movieapp.ui.models.dialog.DialogButton
import jungmal.movieapp.ui.theme.Paddings


@Composable
fun DialogButtonsColumn(
    buttons: List<DialogButton>?
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons?.forEachIndexed { index, dialogButton ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(Paddings.large))
            }
            when (dialogButton) {
                is DialogButton.Primary -> {
                    PrimaryButton (
                        text = dialogButton.title,
                        leadingIconData = dialogButton.leadingIconData
                    ) { dialogButton.action?.invoke() }
                }
                is DialogButton.Secondary -> {
                    SecondaryButton (
                        text = dialogButton.title
                    ) { dialogButton.action?.invoke() }
                }
                is DialogButton.SecondaryBorderless -> {
                    SecondaryBorderlessButton (
                        text = dialogButton.title
                    ) { dialogButton.action?.invoke() }
                }
                is DialogButton.UnderlinedText -> {
                    UnderlinedTextButton (
                        text = dialogButton.title
                    ) { dialogButton.action?.invoke() }
                }
            }
        }
    }
}