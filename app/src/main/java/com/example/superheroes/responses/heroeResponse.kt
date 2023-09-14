package com.example.superheroes.responses

import com.google.gson.annotations.SerializedName

data class heroeResponse (
    @SerializedName("response"    ) var response    : String?      = null,
    @SerializedName("id"          ) var id          : String?      = null,
    @SerializedName("name"        ) var name        : String?      = null,
    @SerializedName("powerstats"  ) var powerstats  : Powerstats?  = Powerstats(),
    @SerializedName("biography"   ) var biography   : Biography?   = Biography(),
    @SerializedName("appearance"  ) var appearance  : Appearance?  = Appearance(),
    @SerializedName("work"        ) var work        : Work?        = Work(),
    @SerializedName("connections" ) var connections : Connections? = Connections(),
    @SerializedName("image"       ) var image       : Image?       = Image()
)

data class Powerstats (
    @SerializedName("intelligence" ) var intelligence : String? = null,
    @SerializedName("strength"     ) var strength     : String? = null,
    @SerializedName("speed"        ) var speed        : String? = null,
    @SerializedName("durability"   ) var durability   : String? = null,
    @SerializedName("power"        ) var power        : String? = null,
    @SerializedName("combat"       ) var combat       : String? = null
)

data class Biography (
    @SerializedName("full-name"        ) var full_name        : String?           = null,
    @SerializedName("alter-egos"       ) var alter_egos       : String?           = null,
    @SerializedName("aliases"          ) var aliases          : ArrayList<String> = arrayListOf(),
    @SerializedName("place-of-birth"   ) var place_of_birth   : String?           = null,
    @SerializedName("first-appearance" ) var first_appearance : String?           = null,
    @SerializedName("publisher"        ) var publisher        : String?           = null,
    @SerializedName("alignment"        ) var alignment        : String?           = null
)

data class Appearance (
    @SerializedName("gender"     ) var gender     : String?           = null,
    @SerializedName("race"       ) var race       : String?           = null,
    @SerializedName("height"     ) var height     : ArrayList<String> = arrayListOf(),
    @SerializedName("weight"     ) var weight     : ArrayList<String> = arrayListOf(),
    @SerializedName("eye-color"  ) var eye_color  : String?           = null,
    @SerializedName("hair-color" ) var hair_color : String?           = null
)

data class Work (
    @SerializedName("occupation" ) var occupation : String? = null,
    @SerializedName("base"       ) var base       : String? = null
)

data class Connections (
    @SerializedName("group-affiliation" ) var group_affiliation : String? = null,
    @SerializedName("relatives"         ) var relatives         : String? = null
)

data class Image (
    @SerializedName("url" ) var url : String? = null
)