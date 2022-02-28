package net.blumia.pineapple.lockscreen.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.blumia.pineapple.lockscreen.R

@Composable
fun HomeScreen(
    showDialog: Boolean = false,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onOpenA11ySettingsBtnClicked: () -> Unit = {},
    onLockScreenBtnClicked: () -> Unit = {},
    onLockScreenBtnLongPressed: () -> Unit = {},
    onCreateShortcutBtnClicked: () -> Unit = {},
    onActionSettingsClicked: () -> Unit = {},
    onActionAboutClicked: () -> Unit = {},
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                actions = {
                    var expanded by remember { mutableStateOf(false)}
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = stringResource(id = R.string.menu))
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(onClick = onActionSettingsClicked) {
                            Text(stringResource(id = R.string.settings))
                        }
                        DropdownMenuItem(onClick = onActionAboutClicked) {
                            Text(stringResource(id = R.string.about))
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onLockScreenBtnClicked,
                icon = {
                    Icon(Icons.Filled.Lock, contentDescription = null)
                },
                text = {
                    Text(stringResource(id = R.string.fab_lock_screen))
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            AccessibilityPermissionCard(onOpenA11ySettingsBtnClicked)

            CreateShortcutIconCard(onCreateShortcutBtnClicked)
        }
    }
}

@Composable
fun MainScreenCard(
    descriptionText: String,
    actionText: String,
    onActionClicked: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
        ) {
            Text(
                modifier = Modifier.padding(6.dp),
                style = MaterialTheme.typography.body1,
                text = descriptionText
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TextButton(onClick = onActionClicked) {
                    Text(text = actionText)
                }
            }
        }
    }
}

@Composable
fun AccessibilityPermissionCard(
    onOpenA11ySettingsBtnClicked: () -> Unit = {}
) {
    MainScreenCard(
        descriptionText = stringResource(id = R.string.card_a11y_description),
        actionText = stringResource(id = R.string.card_a11y_action_go_to_setting),
        onActionClicked = onOpenA11ySettingsBtnClicked
    )
}

@Composable
fun CreateShortcutIconCard(
    onLockScreenBtnClicked: () -> Unit = {}
) {
    MainScreenCard(
        descriptionText = stringResource(id = R.string.card_shortcut_description),
        actionText = stringResource(id = R.string.card_shortcut_action_create_shortcut),
        onActionClicked = onLockScreenBtnClicked
    )
}

@Composable
@Preview(showBackground = true)
fun AccessibilityPermissionCardPreview() {
    AccessibilityPermissionCard()
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}