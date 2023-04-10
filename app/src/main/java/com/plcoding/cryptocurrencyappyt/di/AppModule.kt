package com.plcoding.cryptocurrencyappyt.di

import android.app.Application
import androidx.room.Room
import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.data.data_source.FavoriteProductDatabase
import com.plcoding.cryptocurrencyappyt.data.remote.FakeStoreApi
import com.plcoding.cryptocurrencyappyt.data.repository.ProductRepositoryImpl
import com.plcoding.cryptocurrencyappyt.domain.repository.ProductRepository
import com.plcoding.cryptocurrencyappyt.domain.use_case.wish_list.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFakeStoreApi(): FakeStoreApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FakeStoreApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        api: FakeStoreApi,
        db: FavoriteProductDatabase
    ): ProductRepository {
        return ProductRepositoryImpl(api, db.favoriteProductDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteProductDatabase(app: Application): FavoriteProductDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteProductDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteProductUseCase(repository: ProductRepository): FavoriteProductUseCase {
        return FavoriteProductUseCase(
            getProductListUseCase = GetProductListUseCase(repository),
            getWishListUseCase = GetWishListUseCase(repository),
            addWishItemUseCase = AddWishItemUseCase(repository),
            getProductCountUseCase = GetProductCountUseCase(repository),
            updateWishedProductUseCase = UpdateWishedProductUseCase(repository)
        )
    }
}