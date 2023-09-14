package com.example.superheroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.superheroes.responses.apiSuperHeroes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetalleFragment : Fragment() {

    val args:DetalleFragmentArgs by navArgs()

    private lateinit var heroeNombre:TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idSuperHeroe=args.idSH
        buscarHeroe(idSuperHeroe)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root= inflater.inflate(R.layout.fragment_detalle, container, false)

        heroeNombre=root.findViewById(R.id.txtNombre)

        return root
    }

    private fun buscarHeroe(valor:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call =getRetrofit().create(apiSuperHeroes::class.java).getInfoHeroe("$valor")
            var heroes=call.body()

            activity?.runOnUiThread{
                if(call.isSuccessful){

                    if (heroes != null) {
                        if(!heroes.response.equals("error")){
                            heroeNombre.text=heroes.name
                        }

                    }

                }else{
                    //showError()
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/275595605335486/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}