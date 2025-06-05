package softserve.academy.groupproject
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, password TEXT, password2 TEXT)"
        db!!.execSQL(query)
        val moodQuery = """
            CREATE TABLE moods (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_login TEXT,
                timestamp INTEGER,
                mood INTEGER,
                comment TEXT
            )
        """.trimIndent()
        db.execSQL(moodQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db!!.execSQL("CREATE TABLE moods (id INTEGER PRIMARY KEY AUTOINCREMENT, user_login TEXT, timestamp INTEGER, mood INTEGER, comment TEXT)")
        }
    }


    fun addUser(user: User){
        val values = ContentValues()
        values.put("login", user.login)
        values.put("password", user.password)
        values.put("password2", user.password2)

        val db = this.writableDatabase
        db.insert("users",null, values)
        db.close()
    }
    fun getUser(login:String, password: String): Boolean {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM users WHERE login = ? AND password = ?", arrayOf(login, password))
        val exists = result.moveToFirst()
        result.close()
        return exists
    }
    // Збереження настрою по користувачу
    fun saveMood(login: String, timestamp: Long, mood: Int, comment: String?) {
        val values = ContentValues()
        values.put("user_login", login)
        values.put("timestamp", timestamp)
        values.put("mood", mood)
        values.put("comment", comment)

        val db = this.writableDatabase
        db.insert("moods", null, values)
        db.close()
    }
    // Отримання настроїв користувача
    fun getMoodData(login: String): List<Pair<Long, Int>> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT timestamp, mood FROM moods WHERE user_login = ? ORDER BY timestamp ASC", arrayOf(login))
        val list = mutableListOf<Pair<Long, Int>>()
        if (cursor.moveToFirst()) {
            do {
                val timestamp = cursor.getLong(0)
                val mood = cursor.getInt(1)
                list.add(timestamp to mood)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

}