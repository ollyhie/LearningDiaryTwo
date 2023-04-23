package com.example.learningDiary.data

import androidx.room.*
import com.example.learningDiary.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert
    suspend fun add(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * from movie")
    fun readAll(): Flow<List<Movie>>

    @Query("SELECT * from movie where favoured = 1")
    fun readAllFavoured(): Flow<List<Movie>>

    @Query("SELECT * from movie where id = :movieID")
    fun getMovieByID(movieID: String?): Movie

    @Query("DELETE from movie")
    fun deleteAll()

}