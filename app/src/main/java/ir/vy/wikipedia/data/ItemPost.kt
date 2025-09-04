package ir.vy.wikipedia.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemPost(

    // explore
    val webUrlExplore: String,
    val imgUrl: String,
    val txtTitle: String,
    val txtSubtitle: String,
    val txtDetail: String,

    // trend
    val webUrlTrend: String,
    val imgUrlTrend: String,
    val txtTitleTrend: String,
    val txtSubtitleTrend: String,
    val txtDetailTrend: String,
    val txtNumberTrend: String,
    val txtInsight: String
) : Parcelable