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

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)

        val userLogin: EditText = findViewById(R.id.user_login_auth)
        val userPassword: EditText = findViewById(R.id.user_password_auth)
        val button: Button = findViewById(R.id.button_auth)
        val linkReg: TextView = findViewById(R.id.link_reg)

        linkReg.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        button.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Не всі поля заповнені", Toast.LENGTH_LONG).show()
            }
            else {
                val db = DbHelper(this, null)
                val isAuth = db.getUser(login, password)

                if(isAuth) {
                    Toast.makeText(this, "Користувач $login авторизован", Toast.LENGTH_LONG).show()
                    userLogin.text.clear()
                    userPassword.text.clear()
                    val intent = Intent(this, ItemsActivity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(this, "Користувач $login НЕ авторизован", Toast.LENGTH_LONG).show()
            }
        }
    }

}