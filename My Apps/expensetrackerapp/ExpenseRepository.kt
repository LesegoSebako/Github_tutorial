package com.example.expensetrackerapp

import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: ExpenseDao) {
    val allExpenses: Flow<List<Expense>> = expenseDao.getAllExpenses()
    val allCategories: Flow<List<Category>> = expenseDao.getAllCategories()

    suspend fun addExpense(expense: Expense) = expenseDao.insertExpense(expense)
    suspend fun addCategory(category: Category) = expenseDao.insertCategory(category)
}