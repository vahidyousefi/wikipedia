package ir.vy.wikipedia.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.vy.wikipedia.activities.EditProfileActivity
import ir.vy.wikipedia.activities.SettingsActivity
import ir.vy.wikipedia.databinding.FragmentBottomMoreBinding
import androidx.core.net.toUri

class FragmentBottomMore : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomMoreBinding.inflate(layoutInflater, container, false)

        binding.closeBottomSheet.setOnClickListener {
            dismiss()
        }

        binding.settings.setOnClickListener {
            val goToSettings = Intent(context, SettingsActivity::class.java)
            startActivity(goToSettings)
        }

        binding.openEditProfile.setOnClickListener {

            val openProfile = Intent(context, EditProfileActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(openProfile)
        }

        // go to web Donate
        binding.donate.setOnClickListener {

            val url =
                "https://donate.wikimedia.org/w/index.php?title=Special:LandingPage&country=IR&uselang=en&wmf_medium=WikipediaApp&wmf_source=appmenu&wmf_campaign=Android&app_version=2.7.50543-r-2025-07-31"
            val urlDonate = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(urlDonate)
        }

        return binding.root
    }

}