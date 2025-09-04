package ir.vy.wikipedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ir.vy.wikipedia.R
import ir.vy.wikipedia.databinding.FragmentVideomakerBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FragmentVideomaker : Fragment() {
    private lateinit var binding: FragmentVideomakerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideomakerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(R.drawable.ic_vm)
            .transform(RoundedCornersTransformation(32, 4))
            .into(binding.imgView)
    }

}