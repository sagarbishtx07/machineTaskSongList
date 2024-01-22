package com.example.machinetesttask.task1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.machinetesttask.task1.repo.Repository

class TracksViewModelProviderFactory(
    val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TracksViewModel(repository) as T
    }
}