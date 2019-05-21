package com.raihan.mvpKotlin.model
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movies(@SerializedName("id")
                  @ColumnInfo(name = "id")
                  @PrimaryKey
                  var id:String,
                  @SerializedName("vote_average")
                  @ColumnInfo(name = "vote_average")
                  var vote_average:String,
                  @SerializedName("vote_count")
                  @ColumnInfo(name = "vote_count")
                  var vote_count:String,
                  @SerializedName("video")
                  @ColumnInfo(name = "video")
                  var video:String,
                  @SerializedName("media_type")
                  @ColumnInfo(name = "media_type")
                  var media_type:String,
                  @SerializedName("title")
                  @ColumnInfo(name = "title")
                  var title:String,
                  @SerializedName("popularity")
                  @ColumnInfo(name = "popularity")
                  var popularity:String,
                  @SerializedName("poster_path")
                  @ColumnInfo(name = "poster_path")
                  var poster_path:String,
                  @SerializedName("original_language")
                  @ColumnInfo(name = "original_language")
                  var original_language:String,
                  @SerializedName("original_title")
                  @ColumnInfo(name = "original_title")
                  var original_title:String,
                  @SerializedName("backdrop_path")
                  @ColumnInfo(name = "backdrop_path")
                  var backdrop_path:String,
                  @SerializedName("adult")
                  @ColumnInfo(name = "adult")
                  var adult:String,
                  @SerializedName("overview")
                  @ColumnInfo(name = "overview")
                  var overview:String,
                  @SerializedName("release_date")
                  @ColumnInfo(name = "release_date")
                  var release_date:String)
