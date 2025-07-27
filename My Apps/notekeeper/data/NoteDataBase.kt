package com.example.notekeeper.data

import androidx.room.Database
import android.room.RoomDatabase
import androidx.room.RoomDatabase

@Database(entitie = [Note:: class], version = 1)
abstract class NoteDataBase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
}