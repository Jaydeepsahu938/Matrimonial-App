package Network

import com.example.example.ProfileMatchesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getProfiles(
        @Query("results") results: Int = 20,
        @Query("page") page: Int
    ): ProfileMatchesModel
}

