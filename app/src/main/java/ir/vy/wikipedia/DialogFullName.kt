package ir.vy.wikipedia

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ir.vy.wikipedia.function.showToast
import ir.vy.wikipedia.databinding.DialogFullnameBinding

class DialogFullName(private val dialogFullName: DialogFullNameEvent) : DialogFragment() {
    private lateinit var binding: DialogFullnameBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = AlertDialog.Builder(requireContext()).create()

        binding = DialogFullnameBinding.inflate(layoutInflater, null, false)
        dialog.setView(binding.root)

        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnAccept.setOnClickListener {

            val name = binding.edtName.text.toString()
            val lastName = binding.edtLastName.text.toString()

            if (name.isNotEmpty() && lastName.isNotEmpty()) {

                dialog.dismiss()
                dialogFullName.sendTextData("$name $lastName")
            } else {

                requireContext().showToast("Please enter name & lastname")
            }
        }
        return dialog
    }

    interface DialogFullNameEvent {
        fun sendTextData(data: String)
    }
}