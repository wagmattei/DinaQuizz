package com.wagmattei.dynaquiz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wagmattei.dynaquiz.data.dao.UserDao
import com.wagmattei.dynaquiz.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE_ROOM: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tmpInstance = INSTANCE_ROOM
            if(tmpInstance != null) {
                return tmpInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user"
                        ).build()
                INSTANCE_ROOM = instance
                return instance
            }
        }
    }

}