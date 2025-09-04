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
import ir.vy.wikipedia.databinding.ActivityExploreBinding
import ir.vy.wikipedia.fragments.SEND_DATA_TO_SECOND

class ExploreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // no hide icons in status bar
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        // show toolbar
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

        // auto padding fab from navigation system
        ViewCompat.setOnApplyWindowInsetsListener(binding.fabOpenWeb) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            (view.layoutParams as? ViewGroup.MarginLayoutParams)?.let { params ->
                params.bottomMargin = bottom + 24
                view.layoutParams = params
            }
            insets
        }

        // get data Explore
        val dataPostExplore = intent.getParcelableExtra<ItemPost>(SEND_DATA_TO_SECOND)
        if (dataPostExplore != null) {

            showDataExplore(dataPostExplore)
        }
    }

    private fun showDataExplore(itemPost: ItemPost) {

        // text in toolbar in scroll
        binding.collapsingToolbar.title = itemPost.txtTitle

        // Explore
        Glide.with(this)
            .load(itemPost.imgUrl)
            .into(binding.imgDetail)

        binding.txtDetailTitle.text = itemPost.txtTitle
        binding.txtDetailSubtitle.text = itemPost.txtSubtitle
        binding.txtDetailText.text = itemPost.txtDetail

        // open the Website
        binding.fabOpenWeb.setOnClickListener {

            val url = itemPost.webUrlExplore
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