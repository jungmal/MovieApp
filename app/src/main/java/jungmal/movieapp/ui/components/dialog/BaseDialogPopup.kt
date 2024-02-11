package jungmal.movieapp.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import jungmal.movieapp.ui.components.dialog.components.button.DialogButtonsColumn
import jungmal.movieapp.ui.components.dialog.components.content.DialogContentWrapper
import jungmal.movieapp.ui.components.dialog.components.title.DialogTitleWrapper
import jungmal.movieapp.ui.models.dialog.DialogButton
import jungmal.movieapp.ui.models.dialog.DialogContent
import jungmal.movieapp.ui.models.dialog.DialogTitle
import jungmal.movieapp.ui.theme.Paddings
import jungmal.movieapp.ui.theme.currentColorScheme

@Composable
fun BaseDialogPopup(
    dialogTitle: DialogTitle? = null,
    dialogContent: DialogContent? = null,
    buttons: List<DialogButton>? =  null,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.currentColorScheme.background,
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
           dialogTitle?.let {
               DialogTitleWrapper(it)
           }
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(
                        start = Paddings.large,
                        end = Paddings.large,
                        bottom = Paddings.large,
                    )
            ) {
                dialogContent?.let { DialogContentWrapper(it) }
                buttons?.let { DialogButtonsColumn(it) }
            }
        }

    }
}
