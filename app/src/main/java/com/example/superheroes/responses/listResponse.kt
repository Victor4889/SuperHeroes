package com.example.superheroes.responses

import com.google.gson.annotations.SerializedName

data class listResponse (
    @SerializedName("response") var response:String,
    @SerializedName("id") var id: String,
    @SerializedName("name") var name:String,
    @SerializedName("url") var url:String
)