package com.example.keystore_hugolopezfernandez.repositorios

import com.example.keystore_hugolopezfernandez.modelo.DatosPrivados
import com.example.keystore_hugolopezfernandez.modelo.RespuestaApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RepoApi {
    @GET("api/datosPrivados/{uid}")
    suspend fun getDatosPrivados(@Path("uid") uid: String): Response<RespuestaApi<List<DatosPrivados>>>

    @GET("api/datosPrivados/{id}/{uid}")
    suspend fun getDatoPrivado(@Path("id") id: String, @Path("uid") uid: String): Response<RespuestaApi<DatosPrivados>>

    @POST("api/datosPrivados")
    suspend fun postDatoPrivado(@Body datos: DatosPrivados): Response<RespuestaApi<DatosPrivados>>

    @PUT("api/datosPrivados/{id}")
    suspend fun putDatoPrivado(@Path("id") id: String, @Body datos: DatosPrivados): Response<RespuestaApi<DatosPrivados>>

    @DELETE("api/datosPrivados/{id}")
    suspend fun deleteDatoPrivado(@Path("id") id: String): Response<RespuestaApi<DatosPrivados>>
}