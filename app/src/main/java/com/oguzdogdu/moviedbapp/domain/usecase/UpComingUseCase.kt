package com.oguzdogdu.moviedbapp.domain.usecase

import com.oguzdogdu.moviedbapp.data.asMovie
import com.oguzdogdu.moviedbapp.domain.model.NetworkMovie
import com.oguzdogdu.moviedbapp.domain.repository.MovieRepoInterface
import com.oguzdogdu.moviedbapp.util.Constants.UP_COMING_PAGE
import com.oguzdogdu.moviedbapp.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpComingUseCase @Inject constructor(private val repo: MovieRepoInterface) {
    operator fun invoke(): Flow<Result<List<NetworkMovie>>> = flow {
        try {
            emit(Result.Loading())
            val data = repo.getUpcomingMovies(UP_COMING_PAGE)
            val domainData =
                data.results.map {
                    it.asMovie()
                }
            emit(Result.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Result.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Result.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }
}