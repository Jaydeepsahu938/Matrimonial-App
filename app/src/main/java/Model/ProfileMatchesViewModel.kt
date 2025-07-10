package Model

import RoomDB.ProfileEntity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfileMatchesViewModel(private val repo: ProfilesMatchesRepository) : ViewModel() {
    val profiles: LiveData<List<ProfileEntity>> = repo.localProfiles
    private val errorMessage = MutableLiveData<String>()
    private var currentPage = 1
    private var isLoading = false

    init {
        fetchUsers() // laod first page
    }

    fun fetchUsers() {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            try {
                repo.fetchProfilesFromApi(currentPage)
                currentPage++
            } catch (e: Exception) {
                errorMessage.value = "Failed to fetch users:${e.message}"
            }finally {
                isLoading = false
            }
        }
    }

    fun updateDecision(profile: ProfileEntity, status: Int) {
        viewModelScope.launch {
            repo.updateUserDecision(profile, status)
        }
    }
}