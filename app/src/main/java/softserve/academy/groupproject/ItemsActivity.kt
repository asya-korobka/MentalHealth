package softserve.academy.groupproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_items)

        val itemsList:RecyclerView=findViewById(R.id.itemsList)
        val items = arrayListOf<Item>()

        items.add(Item(1,"mari","Марія","Психолог Марія спеціалізується на роботі з тривожними розладами у молоді. Вона використовує сучасні методи когнітивно-поведінкової терапії, які допомагають швидко позбутися тривоги та повернути спокій у життя \n" +
                "Марія обрала професію психолога, бо сама пережила складний період у житті. Тепер вона допомагає іншим людям знайти внутрішню гармонію і впоратися з труднощами. Марія вірить, що кожна людина має потенціал для змін і готова супроводжувати своїх клієнтів на цьому шляху.",600))
        items.add(Item(2,"olena","Олена","Психолог Олена спеціалізується на роботі з тривожними розладами у молоді. Вона використовує сучасні методи когнітивно-поведінкової терапії, які допомагають швидко позбутися тривоги та повернути спокій у життя",700))
        items.add(Item(3,"valera","Валерій","Психолог Валерій спеціалізується на роботі з тривожними розладами у молоді. Вона використовує сучасні методи когнітивно-поведінкової терапії, які допомагають швидко позбутися тривоги та повернути спокій у життя",600))
        items.add(Item(3,"oksana","Оксана","Психолог Оксана спеціалізується на роботі з тривожними розладами у молоді. Вона використовує сучасні методи когнітивно-поведінкової терапії, які допомагають швидко позбутися тривоги та повернути спокій у життя",750))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)

    }
}