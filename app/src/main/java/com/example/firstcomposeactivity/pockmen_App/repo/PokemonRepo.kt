package com.example.firstcomposeactivity.pockmen_App.repo

import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.pockmen_App.api.PokemonApi
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class PokemonRepo @Inject constructor(val pokemonApi: PokemonApi) {
    suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonList?>?{
        var resultOfApi: Result<PokemonList?>? = null
        try {
            var result = pokemonApi.getPockmentList(limit, offset)
            if(result.isSuccessful && result.body() != null)
            {
                resultOfApi =  Result.success(result.body())
            }
        }
        catch (e: Exception)
        {
            return Result.failure(e)
        }
        return resultOfApi
    }

    suspend fun getPokemonDetail(pokemonNae: String?): Flow<DataState<Pokemon>> {

        return flow {
            try {
                emit(DataState.Loading)
                var data = pokemonApi.getPockmenInfo(pokemonNae)
                if(data.isSuccessful && data.body()!= null) {
                    data.body()?.let {
                        emit(DataState.Success(it))
                    }
                }
                else if(data.errorBody() != null)
                {
                    val errorObj = JSONObject(data.errorBody()!!.charStream().readText())
                    emit(DataState.Error(message = errorObj.getString("message")))
                }
                else
                {
                    emit(DataState.Error(message = "Something Went Wrong"))
                }
            } catch (exception: Exception)
            {
                emit(DataState.Error(message = exception.message))
            }
        }
    }
}