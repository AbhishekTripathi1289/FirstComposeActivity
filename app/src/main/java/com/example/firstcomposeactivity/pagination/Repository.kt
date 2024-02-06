package com.plcoding.composepagingyt

import com.example.firstcomposeactivity.pagination.ListItem
import kotlinx.coroutines.delay

class Repository {

    private val remoteDataSource = (1..100).map {
        ListItem(
            title = "Item $it",
            description = "Description $it"
        )
    }

    suspend fun <T> getItems(page: Int, pageSize: Int): Result<List<T>> {
        delay(2000L)
        val startingIndex = page * pageSize
        return if(startingIndex + pageSize <= remoteDataSource.size) {
            Result.success(
                remoteDataSource.slice(startingIndex until startingIndex + pageSize) as List<T>
            )
        } else Result.success(emptyList())
    }
}