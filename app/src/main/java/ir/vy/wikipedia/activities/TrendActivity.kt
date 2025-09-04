package ir.vy.wikipedia.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import ir.vy.wikipedia.data.ItemPost
import ir.vy.wikipedia.databinding.ActivityTrendBinding
import ir.vy.wikipedia.fragments.SEND_DATA_TO_SECOND

class TrendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTrendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // auto padding fab from navigation system
        ViewCompat.setOnApplyWindowInsetsListener(binding.fabOpenWeb) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            (view.layoutParams as? ViewGroup.MarginLayoutParams)?.let { params ->
                params.bottomMargin = bottom + 24
                view.layoutParams = params
            }
            insets
        }

        // hide Extended Fab in NestedScroll (without text)
        binding.nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY && binding.fabOpenWeb.isExtended) {
                binding.fabOpenWeb.shrink()
            } else if (scrollY < oldScrollY && !binding.fabOpenWeb.isExtended) {
                binding.fabOpenWeb.extend()
            }
        }

        // no hide icons in status bar
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        setSupportActionBar(binding.tollbar2)

        // Hide title in image collapsing
        binding.collapsingToolbar.setExpandedTitleColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )
        // set title collapsing in scroll
        binding.collapsingToolbar.title = ""
        // create icon arrow back
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // get data Trend
        val dataPostTrend = intent.getParcelableExtra<ItemPost>(SEND_DATA_TO_SECOND)
        if (dataPostTrend != null) {

            showDataTrend(dataPostTrend)
        }
    }

    private fun showDataTrend(itemPost: ItemPost) {

        // text in toolbar in scroll
        binding.collapsingToolbar.title = itemPost.txtTitleTrend

        // Trend
        Glide.with(this)
            .load(itemPost.imgUrlTrend)
            .into(binding.imgDetailTrend)

        binding.txtDetailTitle.text = itemPost.txtTitleTrend
        binding.txtDetailSubtitle.text = itemPost.txtSubtitleTrend
        binding.txtDetailTrend.text = itemPost.txtDetailTrend

        // open the Website
        binding.fabOpenWeb.setOnClickListener {

            val url = itemPost.webUrlTrend
            // intent for open web
            val openWeb = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(openWeb)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // item back
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }
}