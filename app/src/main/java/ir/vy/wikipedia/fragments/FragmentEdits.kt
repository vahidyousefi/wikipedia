package ir.vy.wikipedia.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import ir.vy.wikipedia.databinding.FragmentEditsBinding

class FragmentEdits : Fragment() {
    private lateinit var binding: FragmentEditsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://en.m.wikipedia.org/wiki/Help:Introduction_to_editing_with_Wiki_Markup/1"
        binding.btnLearnMore.setOnClickListener {

            val openWeb = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(openWeb)
        }
    }
}