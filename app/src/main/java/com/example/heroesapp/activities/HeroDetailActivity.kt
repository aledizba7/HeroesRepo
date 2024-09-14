package com.example.heroesapp.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.heroesapp.R
import com.example.heroesapp.models.Hero
import com.squareup.picasso.Picasso

class HeroDetailActivity : AppCompatActivity() {
    // Declaración de variables para el nombre, descripción e imagen del héroe seleccionado
    lateinit var heroNameTV: TextView
    lateinit var heroDescriptionTV: TextView
    lateinit var heroImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Configura la interfaz con bordes transparentes
        setContentView(R.layout.activity_hero_detail) // Asigna el layout de la actividad

        // Inicializa las variables con los componentes del layout XML
        heroImage = findViewById(R.id.hero_image)
        heroNameTV = findViewById(R.id.hero_name)
        heroDescriptionTV = findViewById(R.id.hero_description)

        // Obtiene el ID del héroe desde el Intent que inició esta actividad
        val heroId = intent.getIntExtra("heroId", 0)

        // Busca el héroe correspondiente usando el ID
        val hero = Hero.heroes.firstOrNull { hero: Hero ->
            hero.id == heroId
        }

        // Asigna el nombre y la descripción del héroe al TextView correspondiente
        heroNameTV.text = hero?.name
        heroDescriptionTV.text = hero?.description

        // Carga la imagen del héroe usando Picasso y la coloca en el ImageView
        Picasso.get().load(hero?.image).into(heroImage)
    }
}
