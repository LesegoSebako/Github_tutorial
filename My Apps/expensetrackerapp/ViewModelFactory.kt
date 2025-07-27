package com.example.expensetrackerapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetrackerapp.viewmodel.ExpenseViewModel

class ExpenseViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create (modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ExpenseViewModel(application) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}