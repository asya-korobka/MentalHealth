package softserve.academy.groupproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item)
        val title: TextView = findViewById(R.id.item_title)
        val price: TextView = findViewById(R.id.item_price)
        val description: TextView = findViewById(R.id.item_description)
        val imageView: ImageView = findViewById(R.id.item_image)
        val buyButton: Button = findViewById(R.id.button_buy)
        val backButton: ImageButton = findViewById(R.id.button_back)
        title.text = intent.getStringExtra("itemTitle")
        price.text = intent.getStringExtra("itemPrice")
        description.text = intent.getStringExtra("itemDescription")
        val imageName = intent.getStringExtra("itemImage")
        if (!imageName.isNullOrEmpty()) {
            val imageId = resources.getIdentifier(imageName, "drawable", packageName)
            imageView.setImageResource(imageId)
        }
        buyButton.setOnClickListener {
            val intent = Intent(this, ConfirmationActivity::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            finish()
        }
    }
}
