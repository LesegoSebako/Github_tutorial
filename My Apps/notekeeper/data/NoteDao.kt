package com.example.notekeeper.data

import androidx.room.*
import androidx.room.vo.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT *FROM notes")
    fun getAllNotes(): Flow<List<Note>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}