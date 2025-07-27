package com.example.expensetrackerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerapp.AppDatabase
import com.example.expensetrackerapp.Category
import com.example.expensetrackerapp.Expense
import com.example.expensetrackerapp.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExpenseRepository
    val expenses: Flow<List<Expense>>
    val categories: Flow<List<Category>>

    init {
        // Initialize database through AppDatabase instead
        val expenseDao = AppDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
        expenses = repository.allExpenses
        categories = repository.allCategories
    }

    fun addExpense(amount: Double, description: String, categoryId: Int) {
        viewModelScope.launch {
            repository.addExpense(
                Expense(
                    amount = amount,
                    description = description,
                    date = System.currentTimeMillis(),
                    categoryId = categoryId
                )
            )
        }
    }
}