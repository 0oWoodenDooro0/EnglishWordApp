package com.practice.room

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.practice.room.data.WordDatabase
import com.practice.room.data.WordRepository
import com.practice.room.data.WordRepositoryImpl
import com.practice.room.use_case.DeleteWord
import com.practice.room.use_case.GetWords
import com.practice.room.use_case.InsertWord
import com.practice.room.use_case.WordUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWordDatabase(app: Application): WordDatabase {
        return Room.databaseBuilder(
            app,
            WordDatabase::class.java,
            WordDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideWordRepository(database: WordDatabase): WordRepository {
        return WordRepositoryImpl(database.wordDao)
    }

    @Provides
    @Singleton
    fun provideWOrdUseCases(repository: WordRepository, @ApplicationContext context: Context): WordUseCases {
        return WordUseCases(
            getWords = GetWords(repository),
            insertWord = InsertWord(repository, context),
            deleteWord = DeleteWord(repository)
        )
    }
}