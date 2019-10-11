package com.siddhi.lowesgig.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.siddhi.lowesgig.data.local.dao.NewsDao
import com.siddhi.lowesgig.data.local.entity.NewsEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        NewsEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}