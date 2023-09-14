package com.example.superheroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.superheroes.responses.apiSuperHeroes
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetalleFragment : Fragment() {

    val args:DetalleFragmentArgs by navArgs()

    private lateinit var heroeNombre:TextView

    private lateinit var imagenHeroe:ImageView

    private lateinit var intelligence:TextView
    private lateinit var strength:TextView
    private lateinit var speed:TextView
    private lateinit var durability:TextView
    private lateinit var power:TextView
    private lateinit var combat:TextView

    private lateinit var fullName:TextView
    private lateinit var alterEgo:TextView
    private lateinit var aliases:TextView
    private lateinit var birth:TextView
    private lateinit var appearence:TextView
    private lateinit var publisher:TextView
    private lateinit var alignment:TextView

    private lateinit var gender:TextView
    private lateinit var race:TextView
    private lateinit var heights:TextView
    private lateinit var weights:TextView
    private lateinit var eyes:TextView
    private lateinit var hair:TextView

    private lateinit var work:TextView
    private lateinit var base:TextView

    private lateinit var group:TextView
    private lateinit var relatives:TextView
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
        intelligence=root.findViewById(R.id.txtInteligencia)
        strength=root.findViewById(R.id.txtFuerza)
        speed=root.findViewById(R.id.txtVeloz)
        power=root.findViewById(R.id.txtPoder)
        combat=root.findViewById(R.id.txtCombate)
        durability=root.findViewById(R.id.txtDura)
        imagenHeroe=root.findViewById(R.id.imgFoto)

        fullName=root.findViewById(R.id.txtNombreCompleto)
        alterEgo=root.findViewById(R.id.txtEgo)
        aliases=root.findViewById(R.id.txtAlias)
        birth=root.findViewById(R.id.txtNacimiento)
        appearence=root.findViewById(R.id.txtAparicion)
        publisher=root.findViewById(R.id.txtEditorial)
        alignment=root.findViewById(R.id.txtBando)

        gender=root.findViewById(R.id.txtGenero)
        race=root.findViewById(R.id.txtRaza)
        heights=root.findViewById(R.id.txtAltura)
        weights=root.findViewById(R.id.txtPeso)
        eyes=root.findViewById(R.id.txtOjos)
        hair=root.findViewById(R.id.txtCabello)

        work=root.findViewById(R.id.txtOcupacion)
        base=root.findViewById(R.id.txtBase)

        group=root.findViewById(R.id.txtGrupo)
        relatives=root.findViewById(R.id.txtRelacion)

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

                            Picasso.get().load(heroes.image?.url).into(imagenHeroe)

                            intelligence.text= heroes.powerstats?.intelligence ?: "0"
                            strength.text=heroes.powerstats?.strength ?: "0"
                            speed.text=heroes.powerstats?.speed ?: "0"
                            durability.text=heroes.powerstats?.durability ?: "0"
                            power.text=heroes.powerstats?.power ?: "0"
                            combat.text=heroes.powerstats?.combat ?: "0"

                            fullName.text=heroes.biography?.full_name ?: ""
                            alterEgo.text=heroes.biography?.alter_egos ?: ""
                            birth.text=heroes.biography?.place_of_birth ?: ""
                            appearence.text=heroes.biography?.first_appearance ?: ""
                            publisher.text=heroes.biography?.publisher ?: ""
                            alignment.text=heroes.biography?.alignment ?: ""

                            var cad:String=""
                            for(element in heroes.biography?.aliases!!){
                                cad= "$cad$element, "
                            }
                            aliases.text=cad

                            gender.text=heroes.appearance?.gender ?: ""
                            race.text=heroes.appearance?.race ?: ""
                            eyes.text=heroes.appearance?.eye_color ?: ""
                            hair.text=heroes.appearance?.hair_color ?: ""

                            cad=""
                            for(element in heroes.appearance?.height!!){
                                cad= "$cad$element, "
                            }
                            heights.text=cad

                            cad=""
                            for(element in heroes.appearance?.weight!!){
                                cad= "$cad$element, "
                            }
                            weights.text=cad

                            work.text=heroes.work?.occupation ?: ""
                            base.text=heroes.work?.base ?: ""

                            group.text=heroes.connections?.group_affiliation ?: ""
                            relatives.text=heroes.connections?.relatives ?: ""

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