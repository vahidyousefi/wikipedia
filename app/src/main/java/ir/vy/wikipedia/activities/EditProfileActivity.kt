package ir.vy.wikipedia.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ir.vy.wikipedia.DialogFullName
import ir.vy.wikipedia.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity(), DialogFullName.DialogFullNameEvent{
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        binding.backArrow.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
//            finish()
        }

        binding.fullName.setOnClickListener {
            val dialogFullName = DialogFullName(this)
            dialogFullName.show(supportFragmentManager, null)
        }
    }

    override fun sendTextData(data: String) {
        binding.txtFullName.text = data
    }
}