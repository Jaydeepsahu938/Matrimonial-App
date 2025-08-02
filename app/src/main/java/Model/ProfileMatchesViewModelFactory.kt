package Model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProfileMatchesViewModelFactory(
    private val repository: ProfilesMatchesRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileMatchesViewModel(repository) as T
    }
}