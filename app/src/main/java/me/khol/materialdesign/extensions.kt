package me.khol.materialdesign

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

// TODO: Look for [ContextCompat], [MaterialResources] or ktx extensions...

@ColorInt
fun Context.getAttrColor(@AttrRes attrId: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrId, typedValue, true)
    return typedValue.data
}
