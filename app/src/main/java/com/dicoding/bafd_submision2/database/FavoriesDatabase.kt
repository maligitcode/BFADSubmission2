package com.dicoding.bafd_submision2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.bafd_submision2.model.DataUser

@Database(entities = [DataUser::class], version = 1, exportSchema = false)
abstract class FavoriesDatabase: RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriesDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriesDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriesDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriesDatabase::class.java, "fav_database")
                        .build()
                }
            }
            return INSTANCE as FavoriesDatabase
        }
    }
}

//    companion object{
//        @Volatile
//        private var INSTANCE: FavoriesDatabase? = null
//
//        fun getDatabase(context: Context): FavoriesDatabase {
//            val mInstance = INSTANCE
//            if (mInstance != null)
//                return mInstance
//
//            synchronized(FavoriesDatabase::class){
//                val mbuilder = Room.databaseBuilder(
//                    context.applicationContext, FavoriesDatabase::class.java, "db_github"
//                ).build()
//                INSTANCE = mbuilder
//                return mbuilder
//            }
//        }
//    }
