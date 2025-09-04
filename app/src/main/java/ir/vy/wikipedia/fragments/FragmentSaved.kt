package ir.vy.wikipedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import ir.vy.wikipedia.R
import ir.vy.wikipedia.databinding.FragmentSavedBinding

class FragmentSaved : Fragment() {
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ravesh 1 : javab nadad
//        val binding2 = ItemCardBinding.inflate(layoutInflater)
//        binding2.cardSaved.isChecked = true

        // ravesh 2
        val card = view.findViewById<MaterialCardView>(R.id.card_saved)
        card.isChecked = true

        // deselect
        card.setOnClickListener {
            //ravesh 1
            card.isChecked = !card.isChecked

            //ravesh 2 : javab nadad
//            if (card.isChecked) {
//
//                card.isChecked = true
//            } else {
//                card.isChecked = false
//            }
        }
    }
}