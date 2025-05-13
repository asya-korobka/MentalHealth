package softserve.academy.groupproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userPassword: EditText = findViewById(R.id.user_password)
        val userPassword2: EditText = findViewById(R.id.user_password2)
        val button: Button = findViewById(R.id.button_reg)
        val linkAuth: TextView = findViewById(R.id.link_auth)
        linkAuth.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
        button.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()
            val password2 = userPassword2.text.toString().trim()

            if (login.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "Не всі поля заповнені", Toast.LENGTH_LONG).show()
            } else if (password != password2) {
                Toast.makeText(this, "Паролі не збігаються", Toast.LENGTH_LONG).show()
            } else {
                val user = User(login, password, password2)
                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Користувач $login доданий", Toast.LENGTH_LONG).show()
                userLogin.text.clear()
                userPassword.text.clear()
                userPassword2.text.clear()
            }
        }

    }

}
