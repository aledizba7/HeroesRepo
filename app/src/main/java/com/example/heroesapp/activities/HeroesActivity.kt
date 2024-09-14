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
import com.squareup.picasso.Picasso

class HeroesActivity : AppCompatActivity() {
    lateinit var heroNameTV : TextView
    lateinit var heroRecyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_heroes)
        heroNameTV = findViewById(R.id.hero_name)
        heroRecyclerView = findViewById(R.id.hero_recyclerview)
        val heroId = intent.getIntExtra("heroId",0)
        Log.i("Heroes Activity","User clicked on Hero: ${heroId}")

        val hero = Hero.heroes.firstOrNull { hero ->
            hero.id == heroId
        }
        Log.i("Hero Activity",hero.toString())
        heroNameTV.text = hero?.name
        val heroes = Hero.heroes.filter { hero ->
            hero.heroId == heroId
        }
        Log.i("Hero Activity",heroes.toString())
        heroRecyclerView.adapter = HeroesAdapter(heroes){ hero->
            val intent = Intent(this@HeroesActivity,HeroDetailActivity::class.java)
            intent.putExtra("heroId",hero.id)
            startActivity(intent)
        }
        heroRecyclerView.layoutManager = GridLayoutManager(this,2)
    }
}