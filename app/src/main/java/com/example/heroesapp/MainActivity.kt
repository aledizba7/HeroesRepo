package com.example.heroesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.heroesapp.activities.PublisherActivity
import com.example.heroesapp.models.User
import com.google.android.material.snackbar.Snackbar
class MainActivity : AppCompatActivity() {
    // Declaración de variables para las entradas de email, contraseña y el botón de login
    lateinit var emailInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Configura la interfaz con los bordes transparentes
        setContentView(R.layout.activity_main) // Asigna el layout de la actividad

        // Accede a las SharedPreferences para obtener el estado de inicio de sesión
        val sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE)
        val isLogged = sharedPreferences.getBoolean("isLogged", false)

        // Si el usuario ya ha iniciado sesión, redirige a la actividad PublisherActivity
        if (isLogged) {
            val intent = Intent(this@MainActivity, PublisherActivity::class.java)
            startActivity(intent)
            finish()
        }

        Log.i("IS_LOGGED", isLogged.toString()) // Log para verificar el estado de inicio de sesión

        // Inicializa las variables con los componentes del layout XML
        emailInput = findViewById(R.id.emailET)
        passwordInput = findViewById(R.id.passwordET)
        loginBtn = findViewById(R.id.btnLogin)

        // Configura el listener del botón de login
        loginBtn.setOnClickListener { v ->
            Log.i("LOGGED_IN", "Logging in... :D") // Log de inicio de sesión
            val email = emailInput.text.toString() // Obtiene el valor del campo de email
            val password = passwordInput.text.toString() // Obtiene el valor del campo de contraseña

            Log.i("Email", email) // Log del email
            Log.i("Password", password) // Log de la contraseña

            // Verifica si los campos están vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Log.i("LOGIN_ERROR", "Email Input is Empty") // Log de error por campos vacíos
                Snackbar.make(v, "Empty inputs, check again.", Snackbar.LENGTH_SHORT).show() // Muestra un mensaje de error
                return@setOnClickListener
            }

            // Valida las credenciales de usuario comparando con una lista estática de usuarios
            val isValidUser = User.users.any { user ->
                user.email == email && user.password == password
            }

            // Si las credenciales no son válidas, muestra un mensaje de error
            if (!isValidUser) {
                Log.i("LOGIN_ERROR", "Incorrect input") // Log de error por credenciales incorrectas
                Snackbar.make(v, "Invalid input for Email/Password", Snackbar.LENGTH_SHORT).show() // Muestra mensaje de error
                return@setOnClickListener
            }

            // Si las credenciales son correctas, guarda el estado de inicio de sesión en SharedPreferences
            Log.i("LOGIN_SUCCESSFUL", "Log In SUCCESSFUL!") // Log de éxito en el inicio de sesión
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLogged", true) // Guarda el estado como "logged in"
            editor.apply()

            // Muestra un mensaje de éxito y redirige a la actividad PublisherActivity
            Snackbar.make(v, "Log In SUCCESSFUL!", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, PublisherActivity::class.java)
            startActivity(intent)
            finish() // Termina la actividad actual
        }
    }
}
