package com.example.heroesapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.heroesapp.R
import com.example.heroesapp.models.Publisher
import com.squareup.picasso.Picasso

// Adaptador para el RecyclerView que muestra una lista de Publishers
class PublisherAdapter(
    val publisherList: List<Publisher>, // Lista de publishers que se mostrará
    val onClick: (Publisher) -> Unit // Función que se ejecuta al hacer clic en un publisher
) : RecyclerView.Adapter<PublisherViewHolder>() {

    // Crea un nuevo ViewHolder cuando no hay vistas recicladas disponibles
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublisherViewHolder {
        // Infla el layout XML para cada item en el RecyclerView
        val view = LayoutInflater.from(parent.context).inflate(R.layout.publisher_intro, parent, false)
        return PublisherViewHolder(view) // Retorna el ViewHolder con la vista inflada
    }

    // Retorna el número de items en la lista de publishers
    override fun getItemCount(): Int {
        return publisherList.count() // Devuelve el tamaño de la lista
    }

    // Vincula los datos de un publisher con el ViewHolder
    override fun onBindViewHolder(holder: PublisherViewHolder, position: Int) {
        val publisher = publisherList[position] // Obtiene el publisher en la posición actual

        // Asigna el nombre y la imagen del publisher a los elementos visuales
        holder.publisherName.text = publisher.name
        Picasso.get().load(publisher.image).into(holder.publisherImage)

        // Configura el evento de clic en el item del RecyclerView
        holder.itemView.setOnClickListener {
            Log.i("PublisherAdapter", "Navigating to Publisher: ${publisher.name}") // Log cuando se selecciona un publisher
            onClick(publisher) // Llama a la función onClick con el publisher seleccionado
        }
    }
}

// ViewHolder para el RecyclerView que contiene las vistas para mostrar los publishers
class PublisherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val publisherName: TextView = view.findViewById(R.id.publisher_name) // TextView para el nombre del publisher
    val publisherImage: ImageView = view.findViewById(R.id.publisher_image) // ImageView para la imagen del publisher
}
