package softserve.academy.groupproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class EmotionalStateActivity : AppCompatActivity() {

    private var hasSelectedMood = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emotion_state)

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

        val moodButtons = mapOf(
            R.id.button100 to "100",
            R.id.button75 to "75",
            R.id.button50 to "50",
            R.id.button25 to "25",
            R.id.button0 to "0"
        )

        // Назначаем обработчики для кнопок выбора настроения
        moodButtons.forEach { (buttonId, moodValue) ->
            findViewById<ImageButton>(buttonId).setOnClickListener {
                if (!hasSelectedMood) {
                    hasSelectedMood = true
                    when (moodValue) {
                        "100", "75" -> {
                            val message = messagesPositive.random()
                            showDialog("Чудово!", message)
                        }
                        else -> {
                            val message = messagesSupportive.random()
                            showDialog("Дякуємо за чесність", message)
                        }
                    }
                } else {
                    Toast.makeText(this, "Ви вже обрали свій стан", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val saveButton: Button = findViewById(R.id.button_save)
        saveButton.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
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
