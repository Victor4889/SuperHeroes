package com.example.superheroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.databinding.ItemListaBinding
import com.squareup.picasso.Picasso

class HeroesAdapter(private val imagenes:List<String>, private val nombres:List<String>, private val listener: OnItemClickListener):RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return HeroesViewHolder(layoutInflater.inflate(R.layout.item_lista,parent,false))
    }

    override fun getItemCount(): Int = imagenes.size

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val item:String=imagenes[position]
        val nombre:String=nombres[position]
        holder.bind(item,nombre)
    }


    inner class HeroesViewHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener {

        private var binding= ItemListaBinding.bind(view)

        fun bind(imagen:String, nombre:String){
            Picasso.get().load(imagen).into(binding.imgHeroe)
            binding.txtNombre.text=nombre
        }

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position=adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position:Int )
    }
}