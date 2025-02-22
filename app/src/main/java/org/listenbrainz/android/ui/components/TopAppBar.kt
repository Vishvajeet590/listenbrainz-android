package org.listenbrainz.android.ui.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.listenbrainz.android.R
import org.listenbrainz.android.ui.screens.dashboard.DashboardActivity
import org.listenbrainz.android.ui.screens.dashboard.DonateActivity
import org.listenbrainz.android.ui.screens.settings.SettingsActivity

@Composable
fun TopAppBar(activity: Activity, title: String = "ListenBrainz") {
    androidx.compose.material.TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon =  {
            when {
                activity::class.java != DashboardActivity::class.java -> {
                    IconButton(onClick = {
                        activity.onBackPressed()
                    }) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "Back")
                    }
                }
                else -> {
                    IconButton(onClick = {
                        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://listenbrainz.org")))
                    }) {
                        Icon(painterResource(id = R.drawable.ic_listenbrainz_logo_icon),
                            "MusicBrainz",
                            tint = Color.Unspecified)
                    }
                }
            }
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onSurface,
        elevation = 2.dp,
        actions = {
            IconButton(onClick = {
                activity.startActivity(Intent(activity, org.listenbrainz.android.ui.screens.about.AboutActivity::class.java))
            }) {
                Icon(painterResource(id = R.drawable.ic_information),
                    "About",
                    tint = Color.Unspecified)
            }
            IconButton(onClick = {
                activity.startActivity(Intent(activity, DonateActivity::class.java))
            }) {
                Icon(painterResource(id = R.drawable.ic_donate), "Donate", tint = Color.Unspecified)
            }
            IconButton(onClick = {
                activity.startActivity(Intent(activity, SettingsActivity::class.java))
            }) {
                Icon(painterResource(id = R.drawable.action_settings),
                    "Settings",
                    tint = Color.Unspecified)
            }
        }
    )
}
