package com.example.lab6plataformasmoviles

data class Concierto(
    val id: String,
    val title: String,
    val location: String,
    val date: String,
    val description: String,
    var isFavorite: Boolean = false,
    val imageResId: Int // nuevo campo para la imagen
)

val conciertos = listOf(
    Concierto("1", "Concierto A", "Auditorio Nacional", "12 de Octubre", "Un concierto memorable", false, R.drawable.concert_image_1),
    Concierto("2", "Concierto B", "Estadio Azteca", "13 de Octubre", "Un concierto espectacular", false, R.drawable.concert_image_2),
    Concierto("3", "Concierto C", "Foro Sol", "14 de Octubre", "Un concierto lleno de energía", false, R.drawable.concert_image_3),
    Concierto("4", "Concierto D", "Palacio de los Deportes", "15 de Octubre", "Una experiencia única", false, R.drawable.concert_image_4),
    Concierto("5", "Concierto E", "Estadio Maya", "16 de Octubre", "Un show increíble", false, R.drawable.concert_image_5)
)
