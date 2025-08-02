package RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileEntity(
    @PrimaryKey val id: String,
    val name: String,
    val age: Int,
    val location: String,
    val imageUrl: String,
    val status: Int = 0, // status = 0 means pending, status = 1 means accepted, status = 2 means rejected
)