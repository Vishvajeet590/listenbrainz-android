package org.listenbrainz.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import org.listenbrainz.android.R
import org.listenbrainz.android.model.CoverArt
import org.listenbrainz.android.model.Listen
import org.listenbrainz.android.ui.theme.lb_purple

@Composable
fun ListenCard(listen: Listen, coverArt: CoverArt?, onItemClicked: (listen: Listen) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClicked(listen) }),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = coverArt?.images?.get(0)?.thumbnails?.large)
                    .placeholder(R.drawable.ic_coverartarchive_logo_no_text)
                    .error(R.drawable.ic_coverartarchive_logo_no_text)
                    .build()
            )

            Image(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painter,
                alignment = Alignment.CenterStart,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = listen.track_metadata.track_name,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = buildString {
                        append(listen.track_metadata.artist_name)
                    },
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colors.surface,
                    style = typography.caption
                )

                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = listen.track_metadata.release_name ?: "",
                        modifier = Modifier.padding(0.dp, 12.dp, 12.dp, 0.dp),
                        color = MaterialTheme.colors.surface,
                        style = typography.caption
                    )
                }
            }
/*  Love/Hate Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_heart_broken_24),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp, 16.dp),
                    tint = Color.Red
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_heart_broken_24),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp, 16.dp),
                    tint = Color.Red
                )
            }
*/
        }
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun ListenCardSmall(
    releaseName: String,
    artistName: String,
    coverArtUrl: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(enabled = true) {
                onClick()
                // Sends the user to recordings page just like website.
            },
        shape = RoundedCornerShape(5.dp),
        shadowElevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            
            // Album cover art
            GlideImage(
                model = coverArtUrl,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Fit,
                contentDescription = "Album Cover Art"
            ) {
                it.placeholder(R.drawable.ic_coverartarchive_logo_no_text)
                    .override(75)
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier) {
                androidx.compose.material3.Text(
                    text = releaseName,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                        .copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = lb_purple,
                            lineHeight = 14.sp
                        ),
                )
                androidx.compose.material3.Text(
                    text = artistName,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                        .copy(
                            fontWeight = FontWeight.Bold,
                            color = lb_purple.copy(alpha = 0.7f)
                        )
                )
            }
        }
    }
}
