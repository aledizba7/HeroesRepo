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

// Adaptador para el RecyclerView que muestra una lista de héroes
class HeroesAdapter(
    val heroList: List<Hero>, // Lista de héroes a mostrar
    val onClick: (Hero) -> Unit // Función a ejecutar cuando se hace clic en un héroe
) : RecyclerView.Adapter<HeroViewHolder>() {

    // Crea un nuevo ViewHolder cuando no hay vistas recicladas disponibles
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        // Infla el layout XML para cada héroe en el RecyclerView
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_preview, parent, false)
        return HeroViewHolder(view) // Retorna el ViewHolder con la vista inflada
    }

    // Retorna el número de elementos en la lista de héroes
    override fun getItemCount(): Int {
        return heroList.count() // Devuelve la cantidad de héroes
    }

    // Vincula los datos de un héroe con el ViewHolder
    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = heroList[position] // Obtiene el héroe en la posición actual

        // Asigna el nombre y la imagen del héroe a los elementos visuales
        holder.heroName.text = hero.name
        Picasso.get().load(hero.image).into(holder.heroImage)

        // Configura el evento de clic en el item del RecyclerView
        holder.itemView.setOnClickListener {
            onClick(hero) // Llama a la función onClick con el héroe seleccionado
        }
    }
}

// ViewHolder para el RecyclerView que contiene las vistas para mostrar los héroes
class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val heroImage: ImageView = view.findViewById(R.id.heroes_image) // ImageView para la imagen del héroe
    val heroName: TextView = view.findViewById(R.id.heroes_name) // TextView para el nombre del héroe
}
