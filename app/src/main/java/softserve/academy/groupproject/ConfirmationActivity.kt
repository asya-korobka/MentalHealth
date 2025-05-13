package softserve.academy.groupproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)
        val backButton: Button = findViewById(R.id.button_back)
        backButton.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
    }
}