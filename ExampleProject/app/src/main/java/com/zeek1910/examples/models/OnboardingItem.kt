package com.zeek1910.examples.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnboardingItem(
    @StringRes
    val titleId: Int,
    @DrawableRes
    val imageId: Int,
    @StringRes
    val subTitleId: Int
)
