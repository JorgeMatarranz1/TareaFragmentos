package com.example.tareafragments.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Asignatura(
        @PrimaryKey val nombreAsig: String,
)