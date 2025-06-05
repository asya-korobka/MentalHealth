package softserve.academy.groupproject

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class EmotionalStateActivity : AppCompatActivity() {

    private var selectedMoodValue: String? = null
    private lateinit var editTextMood: EditText
    private lateinit var saveButton: Button
    private var hasSelectedMood = false
    private lateinit var currentUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emotion_state)

        // Отримуємо поточного користувача з SharedPreferences
        val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
        currentUser = prefs.getString("current_user", "") ?: ""

        val messagesPositive = listOf(
            "Ти молодець! Продовжуй у тому ж дусі!",
            "Раді чути, що ти почуваєшся добре!",
            "Зберігай цей позитивний настрій!"
        )

        val messagesSupportive = listOf(
            "Це нормально почуватися не найкраще. Спробуй зробити щось приємне для себе.",
            "Знайди час для відпочинку. Ти заслуговуєш на турботу.",
            "Поговори з близьким — це може допомогти."
        )

        editTextMood = findViewById(R.id.editTextMood)
        saveButton = findViewById(R.id.button_save)

        val moodButtons = mapOf(
            R.id.button100 to "100",
            R.id.button75 to "75",
            R.id.button50 to "50",
            R.id.button25 to "25",
            R.id.button0 to "0"
        )

        moodButtons.forEach { (buttonId, moodValue) ->
            findViewById<ImageButton>(buttonId).setOnClickListener {
                if (!hasSelectedMood) {
                    hasSelectedMood = true
                    selectedMoodValue = moodValue

                    val message = when (moodValue) {
                        "100", "75" -> messagesPositive.random()
                        else -> messagesSupportive.random()
                    }
                    showDialog("Обраний стан: $moodValue%", message)
                } else {
                    Toast.makeText(this, "Ви вже обрали свій стан", Toast.LENGTH_SHORT).show()
                }
            }
        }

        saveButton.setOnClickListener {
            if (selectedMoodValue == null) {
                Toast.makeText(this, "Будь ласка, оберіть свій настрій", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val comment = editTextMood.text.toString().trim()
            val mood = selectedMoodValue!!.toInt()

            val db = DbHelper(this, null)
            val timestamp = System.currentTimeMillis()
            db.saveMood(currentUser, timestamp, mood, comment)

            Toast.makeText(this, "Збережено: Настрій $mood%, Коментар: \"$comment\"", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MoodChartActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }


}
