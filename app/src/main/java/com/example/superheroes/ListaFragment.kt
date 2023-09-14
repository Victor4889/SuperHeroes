package com.example.superheroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.databinding.ActivityMainBinding
import com.example.superheroes.responses.apiSuperHeroes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaFragment : Fragment(), SearchView.OnQueryTextListener, HeroesAdapter.OnItemClickListener {

    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:HeroesAdapter
    private val heroesImegenes= mutableListOf<String>()
    private val heroesNombres= mutableListOf<String>()
    private val heroesIds= mutableListOf<String>()
    private lateinit var lista:RecyclerView
    private lateinit var buscador:SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_lista, container, false)
        lista= root.findViewById<RecyclerView>(R.id.rvLista)
        buscador= root.findViewById<SearchView>(R.id.svBuscar)

        buscador.setOnQueryTextListener(this)
        cargarListaImagenes()

        heroesNombres.clear()
        heroesImegenes.clear()
        heroesIds.clear()

        var idHeroe:Int=1
        while (idHeroe<=2) {
            buscarImagen(idHeroe.toString())
            idHeroe++
        }

        return root
    }



    private fun cargarListaImagenes(){
        adapter= HeroesAdapter(heroesImegenes,heroesNombres,this)

        lista.layoutManager=LinearLayoutManager(activity)
        lista.adapter=adapter

    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/275595605335486/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buscarImagen(valor:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call =getRetrofit().create(apiSuperHeroes::class.java).getImagen("$valor/image")
            var heroes=call.body()

            activity?.runOnUiThread{
                if(call.isSuccessful){

                    if (heroes != null) {
                        if(!heroes.response.equals("error")){
                            val imgs= mutableListOf<String>()
                            val names= mutableListOf<String>()
                            val idSH= mutableListOf<String>()

                            imgs.add(heroes.url)
                            names.add(heroes.name)
                            idSH.add(heroes.id)

                            //heroesImegenes.clear()
                            heroesImegenes.addAll(imgs)

                            //heroesNombres.clear()
                            heroesNombres.addAll(names)

                            heroesIds.addAll(idSH)

                            adapter.notifyDataSetChanged()

                        }

                    }

                }else{
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(activity, "Ocurrio un error al consumir el api", Toast.LENGTH_SHORT)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            buscarImagen(query.toLowerCase())
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onItemClick(position: Int) {
        val clickedItem=heroesIds[position]
        Toast.makeText(activity,"Item $clickedItem click",Toast.LENGTH_SHORT).show()
        findNavController().navigate(ListaFragmentDirections.actionListaFragmentToDetalleFragment(idSH=clickedItem

        ))

    }
}