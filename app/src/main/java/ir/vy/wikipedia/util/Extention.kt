package ir.vy.wikipedia.util

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// auto padding fab from navigation system
fun autoMargin(yourView: View) {

    ViewCompat.setOnApplyWindowInsetsListener(yourView) { view, insets ->
        val bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        (view.layoutParams as? ViewGroup.MarginLayoutParams)?.let { params ->
            params.bottomMargin = bottom + 24
            view.layoutParams = params
        }
        insets
    }
}
