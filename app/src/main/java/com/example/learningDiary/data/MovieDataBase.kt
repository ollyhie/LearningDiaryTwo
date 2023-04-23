package com.example.learningDiary.data

import android.content.Context
import androidx.compose.runtime.rememberCoroutineScope
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.learningDiary.models.Movie
import com.example.learningDiary.models.getMovies
import com.example.learningDiary.utils.CustomConverters
import com.example.learningDiary.utils.ioThread
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(CustomConverters::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        // Volatile -> Instance var should not be cached in threads
        // so it can't be overwritten by two different threads
        @Volatile
        private var Instance: MovieDataBase? = null

        fun getDatabase(context: Context): MovieDataBase {
            // Synchronized -> ensures that the function cannot be
            // called by more than 1 thread at a time
            return Instance ?: synchronized(this) {
                Instance ?: buildDatabase(context)
                    .also {
                        Instance = it
                    }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context = context,
                klass = MovieDataBase::class.java,
                name = "movie_db"
            )
                //.addCallback(seedDatabaseCallback(context))
                .fallbackToDestructiveMigration()
                .build()

        private fun seedDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    ioThread {
                        //val movieDao = getDatabase(context).movieDao()
                        //getMovies().forEach { movie ->
                         //   movieDao.add(movie = movie)
                        //}
                    }
                }
            }
        }
    }
}