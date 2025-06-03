package softserve.academy.groupproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_items)

        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        val moodChartButton: Button = findViewById(R.id.buttonMoodChart)

        val items = arrayListOf<Item>(
            Item(1, "mari", "Марія", "Психолог Марія спеціалізується на роботі з тривожними розладами у молоді. Вона використовує сучасні методи когнітивно-поведінкової терапії, які допомагають швидко позбутися тривоги та повернути спокій у життя \n" +
                    "Марія обрала професію психолога, бо сама пережила складний період у житті. Тепер вона допомагає іншим людям знайти внутрішню гармонію і впоратися з труднощами. Марія вірить, що кожна людина має потенціал для змін і готова супроводжувати своїх клієнтів на цьому шляху.", 600),
            Item(2, "olena", "Олена", "Психолог Олена спеціалізується на роботі з тривожними розладами у молоді. Вона використовує сучасні методи когнітивно-поведінкової терапії, які допомагають швидко позбутися тривоги та повернути спокій у життя", 700),
            Item(3, "valera", "Валерій", "Психолог Валерій спеціалізується на роботі з тривожними розладами у молоді. Вона використовує сучасні методи когнітивно-поведінкової терапії, які допомагають швидко позбутися тривоги та повернути спокій у життя", 600),
            Item(4, "oksana", "Оксана", "Психолог Оксана спеціалізується на роботі з тривожними розладами у молоді. Вона використовує сучасні методи когнітивно-поведінкової терапії, які допомагають швидко позбутися тривоги та повернути спокій у життя", 750)
        )

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)

        // Обробка натискання кнопки "Графік настрою"
        moodChartButton.setOnClickListener {
            val intent = Intent(this, MoodChartActivity::class.java)
            startActivity(intent)
        }
    }
}
