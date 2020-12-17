package com.example.tareafragmentsjorgematarranz.database

import androidx.room.*
import com.example.tareafragments.database.Alumno
import com.example.tareafragments.database.Asignatura

data class AsignaturaAlumno (
    @Embedded var asignatura: Asignatura,

    @Relation(
        parentColumn = "nombreAsig",
        entity = Alumno::class,
        entityColumn = "idAlumno",
        associateBy = Junction(value = AlumnoAsignaturaCrossRef::class,
            parentColumn = "nombreAsig",
            entityColumn = "idAlumno")
    )

    var alumnos: List<Alumno>
)

@Entity(primaryKeys = ["nombreAsig", "idAlumno"])
data class AlumnoAsignaturaCrossRef(
    val nombreAsig: String,
    val idAlumno: Int
)