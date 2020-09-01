package jcellomarcano.com.parakeetmap.Models.POIs

data class Candidate(
    val formatted_address: String,
    val geometry: Geometry,
    val name: String,
    val opening_hours: OpeningHours,
    val photos: List<Photo>,
    val rating: Double
)