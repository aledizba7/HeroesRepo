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
import com.example.heroesapp.adapters.HeroesAdapter
import com.example.heroesapp.adapters.PublisherAdapter
import com.example.heroesapp.models.Hero
import com.example.heroesapp.models.Publisher
import com.example.heroesapp.models.User
import com.squareup.picasso.Picasso

class HeroesActivity : AppCompatActivity() {
    // Declaración de variables para el nombre del publisher y el RecyclerView de héroes
    lateinit var publisherNameTV: TextView
    lateinit var heroesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Configura la interfaz con bordes transparentes
        setContentView(R.layout.activity_heroes) // Asigna el layout de la actividad

        // Inicializa las variables con los componentes del layout XML
        publisherNameTV = findViewById(R.id.publisher_name)
        heroesRecyclerView = findViewById(R.id.heroes_recyclerview)

        // Obtiene el ID del publisher desde el Intent que inició esta actividad
        val publisherId = intent.getIntExtra("publisherId", 0)
        Log.i("Heroes Activity", "User clicked on Publisher: $publisherId") // Log del ID del publisher

        // Busca el publisher correspondiente usando el ID
        val publisher = Publisher.publishers.firstOrNull { publisher ->
            publisher.id == publisherId
        }

        Log.i("Heroes Activity", publisher.toString()) // Log con la información del publisher
        publisherNameTV.text = publisher?.name // Asigna el nombre del publisher al TextView

        // Filtra los héroes que pertenecen al publisher seleccionado
        val heroes = Hero.heroes.filter { hero ->
            hero.publisherId == publisherId
        }

        Log.i("Heroes Activity", heroes.toString()) // Log de los héroes filtrados

        // Configura el RecyclerView con un adaptador para los héroes filtrados
        heroesRecyclerView.adapter = HeroesAdapter(heroes) { hero ->
            // Cuando se hace clic en un héroe, inicia la actividad HeroDetailActivity
            val intent = Intent(this@HeroesActivity, HeroDetailActivity::class.java)
            intent.putExtra("heroId", hero.id) // Pasa el ID del héroe a la siguiente actividad
            startActivity(intent) // Inicia la actividad HeroDetailActivity
        }

        // Configura el RecyclerView con un layout en forma de cuadrícula de 2 columnas
        heroesRecyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}
