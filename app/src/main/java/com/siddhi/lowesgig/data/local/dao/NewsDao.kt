package com.siddhi.lowesgig.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.siddhi.lowesgig.data.local.db.DBConstants
import com.siddhi.lowesgig.data.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articleList: List<NewsEntity?>)

    @Query("SELECT * FROM " + DBConstants.NEWS_TABLE_NAME)
    fun getAll(): List<NewsEntity?>

}