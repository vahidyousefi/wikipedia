package ir.vy.wikipedia.function

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(text: String) {

    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.snackBar(
    viewMain: View,
    textTitle: String,
    textAction: String?,
    textToast: String?,
    colorActionText: Int,
    colorText: Int,
    backTint: Int,
    snackType: Int,
    snackGravity: Int,
    marginBottom : Int
) {
    val snack = Snackbar
        .make(viewMain, textTitle, snackType)
        .setAction(textAction) {
            if (textToast != null) {
                this.showToast(textToast)
            }
        }
        .setActionTextColor(ContextCompat.getColor(this, colorActionText))
        .setTextColor(ContextCompat.getColor(this, colorText))
        .setBackgroundTint(ContextCompat.getColor(this, backTint))

    snack.view.layoutParams =
        (snack.view.layoutParams as FrameLayout.LayoutParams)
            .apply {
                //example : android.view.Gravity.BOTTOM
                gravity = snackGravity

                bottomMargin = marginBottom
                leftMargin = 42
                rightMargin = 42
            }
    snack.show()
}