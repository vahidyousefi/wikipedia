package ir.vy.wikipedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.vy.wikipedia.R
import ir.vy.wikipedia.function.showToast
import ir.vy.wikipedia.databinding.FragmentSearchBinding

class FragmentSearch : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chipWiki.setOnClickListener {
            requireContext().showToast("wiki selected")
        }

        binding.chipWikimedia.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                requireContext().showToast("chip checked")
            } else {
                requireContext().showToast("chip Unchecked")
            }
        }

        // change in chipGroup
        binding.chipGroupMain.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {

                R.id.chipAndroid -> {
                    requireContext().showToast("Android")
                }
            }
        }

    }
}