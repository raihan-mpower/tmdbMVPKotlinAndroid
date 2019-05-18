package com.raihan.mvpKotlin.model

import com.google.gson.annotations.SerializedName

data class MovieList(@SerializedName("results") var results: List<Movies>) {
}