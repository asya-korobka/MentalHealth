package softserve.academy.groupproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoodChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_chart)

        val moodChartView: MoodChartView = findViewById(R.id.moodChartView)
        val backButton: ImageButton = findViewById(R.id.button_back)


        val prefs = getSharedPreferences("mood_data", MODE_PRIVATE)
        val data = prefs.all.mapNotNull {
            val timestamp = it.key.toLongOrNull()
            val mood = (it.value as? String)?.toIntOrNull()
            if (timestamp != null && mood != null) timestamp to mood else null
        }

        moodChartView.setMoodData(data)

        val buttonGoToPsychologists = findViewById<Button>(R.id.buttonGoToPsychologists)
        buttonGoToPsychologists.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            val intent = Intent(this, EmotionalStateActivity::class.java)
            startActivity(intent)
        }
    }
}
