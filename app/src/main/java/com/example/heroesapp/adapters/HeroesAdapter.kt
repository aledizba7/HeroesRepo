package com.example.heroesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.heroesapp.R
import com.example.heroesapp.models.Hero
import com.squareup.picasso.Picasso

class HeroesAdapter(val heroList : List<Hero>, val onClick : (Hero)->Unit) : RecyclerView.Adapter<HeroViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_preview,parent,false)
        return HeroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heroList.count()
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = heroList[position]
        holder.heroName.text = hero.name
        Picasso.get().load(hero.image).into(holder.heroImage)
        holder.itemView.setOnClickListener {
            onClick(hero)
        }
    }

}

class HeroViewHolder(view: View): ViewHolder(view){
    val heroImage : ImageView = view.findViewById(R.id.heroes_image)
    val heroName : TextView = view.findViewById(R.id.heroes_name)
}