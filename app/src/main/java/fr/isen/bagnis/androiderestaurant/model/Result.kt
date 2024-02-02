package fr.isen.bagnis.androiderestaurant.model

import com.google.gson.annotations.SerializedName


data class Result (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

)