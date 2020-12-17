package com.example.tareafragmentsjorgematarranz.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.tareafragments.database.Asignatura
import com.example.tareafragments.database.Profesor

class AsignaturaProfesor(
        @Embedded var asignatura: Asignatura,

        @Relation(
        parentColumn = "nombreAsig",
        entity = Profesor::class,
        entityColumn = "idProfesor",
        associateBy = Junction(value = ProfesorAsignaturaCrossRef::class,
            parentColumn = "nombreAsig",
            entityColumn = "idProfesor")
    )

    var profesores: List<Profesor>
)

@Entity(primaryKeys = ["nombreAsig", "idProfesor"])
data class ProfesorAsignaturaCrossRef(
    val nombreAsig: String,
    val idProfesor: Int
)