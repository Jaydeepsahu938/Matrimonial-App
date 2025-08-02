package com.example.example

import com.google.gson.annotations.SerializedName


data class ProfileMatchesModel (

  @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf(),
  @SerializedName("info"    ) var info    : Info?          = Info()

)