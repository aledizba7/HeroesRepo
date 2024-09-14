package com.example.heroesapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heroesapp.MainActivity
import com.example.heroesapp.R
import com.example.heroesapp.adapters.PublisherAdapter
import com.example.heroesapp.models.Publisher
import com.example.heroesapp.models.User

class PublisherActivity : AppCompatActivity() {
    // Declaración de variables para el nombre de usuario, botón de cerrar sesión y el RecyclerView
    lateinit var usernameTV: TextView
    lateinit var logoutBtn: ImageView
    lateinit var publishers_recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Configura la interfaz con bordes transparentes
        setContentView(R.layout.activity_publisher) // Asigna el layout de la actividad

        // Accede a las SharedPreferences para obtener el usuario logueado
        val sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE)

        // Inicializa las variables con los componentes del layout XML
        usernameTV = findViewById(R.id.usernameTV)
        logoutBtn = findViewById(R.id.logoutBtn)
        publishers_recyclerview = findViewById(R.id.publishers_recyclerview)

        // Obtiene el email del usuario logueado de las SharedPreferences
        val userEmail = sharedPreferences.getString("LOGGED_USER", null)

        // Busca al usuario logueado en la lista de usuarios
        val loggedUser = User.users.find { it.email == userEmail }

        // Si el usuario está logueado, muestra su nombre en el TextView
        if (loggedUser != null) {
            usernameTV.text = loggedUser.computedName // Muestra el nombre completo del usuario
        } else {
            usernameTV.text = "Hola" // Muestra un saludo genérico si no hay usuario logueado
        }

        // Configura el RecyclerView con un adaptador y una lista de publishers
        publishers_recyclerview.adapter = PublisherAdapter(Publisher.publishers) { publisher ->
            Log.i("The following publisher was clicked: ", publisher.name) // Log cuando se hace clic en un publisher
            val intent = Intent(this@PublisherActivity, HeroesActivity::class.java)
            intent.putExtra("publisherId", publisher.id) // Pasa el ID del publisher a la siguiente actividad
            startActivity(intent) // Inicia la actividad HeroesActivity
        }

        // Establece un layout horizontal para el RecyclerView
        publishers_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Listener del botón de cerrar sesión
        logoutBtn.setOnClickListener {
            Log.i("LOGOUT", "Logging out... bye :)") // Log para cerrar sesión

            // Elimina el estado de inicio de sesión de SharedPreferences
            val editor = sharedPreferences.edit()
            editor.remove("isLogged") // Remueve la bandera de inicio de sesión
            editor.apply()

            // Redirige a la actividad MainActivity (pantalla de login)
            val intent = Intent(this@PublisherActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Termina la actividad actual
        }
    }
}
