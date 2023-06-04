package com.app.vegsty.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.vegsty.R
import com.app.vegsty.ui.theme.UnselectColor
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideImageState

@Composable
fun RecipeImage(
  imageUrl: String?,
  onLoadImageAgainClick: () -> Unit,
  shape: RoundedCornerShape
) {

  val isImageLoading = remember {
    mutableStateOf(false)
  }

  GlideImage(
    imageModel = {
      imageUrl
    },
    imageOptions = ImageOptions(
      contentScale = ContentScale.Crop,
      alignment = Alignment.Center
    ),
    modifier = Modifier
      .fillMaxWidth()
      .height(140.dp)
      .clip(
        shape = shape
      ),
    onImageStateChanged = {
      isImageLoading.value = when (it) {
        is GlideImageState.Loading -> {
          true
        }
        else -> {
          false
        }
      }
    },
    loading = {
      Box(
        modifier = Modifier
          .background(UnselectColor)
          .fillMaxSize()
          .align(Alignment.Center)
      ) {
        CircularProgressIndicator(
          modifier = Modifier
            .align(Alignment.Center)
        )
      }
    },
    failure = {
      Box(
        modifier = Modifier
          .background(UnselectColor)
          .fillMaxSize()
          .align(Alignment.Center)
          .clickable(
            onClick = {
              onLoadImageAgainClick()
            }
          )
      ) {
        if (isImageLoading.value) {
          Icon(
            painter = painterResource(id = R.drawable.ic_reload),
            modifier = Modifier
              .align(Alignment.Center),
            contentDescription = stringResource(id = R.string.common_icon_content_description),
            tint = MaterialTheme.colorScheme.tertiary
          )
        } else {
          Text(
            text = stringResource(id = R.string.common_image_reload_text),
            modifier = Modifier
              .align(Alignment.Center)
          )
        }
      }
    }
  )
}