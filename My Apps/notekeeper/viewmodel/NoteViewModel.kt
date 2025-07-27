package com.example.notekeeper.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notekeeper.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoteViewModel : ViewModel(){

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    fun addNote(note: Note){
        _notes.value += note
    }
    fun deleteNote(noteId: String){
        _notes.value = _notes.value.filter( { it.id != noteId})
    }

    fun getNoteById(id: String): Note?{
        return _notes.value.find { it.id == id}
        }
}