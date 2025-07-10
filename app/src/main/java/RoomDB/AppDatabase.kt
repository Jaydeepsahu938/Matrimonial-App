package RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProfileEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profilesDao(): ProfilesDao

    companion object {
        const val DATABASE_NAME = "profiles_db"

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration(false)
                    .build().also { instance = it }
            }
        }
    }
}