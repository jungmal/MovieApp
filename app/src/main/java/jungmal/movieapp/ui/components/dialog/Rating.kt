package jungmal.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import jungmal.movieapp.ui.components.dialog.DialogPopup
import jungmal.movieapp.ui.models.dialog.DialogButton
import jungmal.movieapp.ui.models.dialog.DialogContent
import jungmal.movieapp.ui.models.dialog.DialogText
import jungmal.movieapp.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Rating(
    restaurantName: String,
    rating: Float,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("Rate your Score!"),
        dialogContent = DialogContent.Rating(
            DialogText.Rating(
                text = restaurantName,
                rating = rating
            )
        ),
        buttons = buttons
    )
}
