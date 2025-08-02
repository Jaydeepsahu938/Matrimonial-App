package Model

import Network.ApiService
import RoomDB.ProfileEntity
import RoomDB.ProfilesDao
import androidx.lifecycle.LiveData
import com.example.example.Location

class ProfilesMatchesRepository(private val apiService: ApiService,private val profilesDao: ProfilesDao) {
    val localProfiles: LiveData<List<ProfileEntity>> = profilesDao.getAllProfiles()

    suspend fun fetchProfilesFromApi(page: Int) {
        val response =  apiService.getProfiles(results = 20, page = page)

        val mappedProfiles = response.results.map {
            ProfileEntity(
                id = it.id.toString(),
                name = it.name?.first + " " + it.name?.last,
                age = it.dob?.age ?: 0,
                location = address(it.location).toString(),
                imageUrl = it.picture?.large ?: "",
                status = 0,
            )
        }
        profilesDao.insertProfile(mappedProfiles)
    }

    private fun address(location: Location?): StringBuilder {
     val address = StringBuilder()
        address.append(location?.city)
        if(address.isNotEmpty())
            address.append(","+location?.state)
        else
            address.append(location?.state)
        if(address.isNotEmpty())
            address.append(","+location?.country)
        else
            address.append(location?.country)
        return address
    }

    suspend fun updateUserDecision(profile: ProfileEntity, status: Int) {
        profilesDao.updateProfile(profile.copy(status = status))
    }
}