package fr.isen.bagnis.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Result (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

): Serializable