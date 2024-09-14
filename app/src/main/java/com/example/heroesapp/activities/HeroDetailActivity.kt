package com.example.heroesapp.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.heroesapp.R
import com.squareup.picasso.Picasso

class HeroDetailActivity : AppCompatActivity() {
    lateinit var heroNameTV: TextView
    lateinit var heroDescriptionTV : TextView
    lateinit var heroImage: ImageView
    // nombre, descripción, imagen del heroe seleccionado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hero_detail)
        heroNameTV = findViewById(R.id.hero_name)
        heroDescriptionTV = findViewById(R.id.hero_description)
        val heroId = intent.getIntExtra("heroId",0)

        val hero = Hero.heroes.firstOrNull { hero: Hero ->
            hero.id == heroId
        }

        heroNameTV.text = hero?.name
        heroDescriptionTV.text = hero?.description
        Picasso.get().load(hero?.image).into(heroImage)
    }
}