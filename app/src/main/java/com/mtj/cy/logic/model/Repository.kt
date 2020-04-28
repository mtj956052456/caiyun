package com.mtj.cy.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/4/28
 */
class PlaceResponse(val status: String, val places: List<Place>)

class Place(
    val name: String,
    val location: Location, @SerializedName("formatted_address") val address: String
)

class Location(val lng: String, val lat: String)
