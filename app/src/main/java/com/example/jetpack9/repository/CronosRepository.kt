package com.example.jetpack9.repository

import androidx.room.Update
import com.example.jetpack9.model.Cronos
import com.example.jetpack9.room.CronosDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CronosRepository @Inject constructor(private val cronosDatabaseDao: CronosDatabaseDao) {

    suspend fun addCrono(crono:Cronos) = cronosDatabaseDao.insert(crono)

    suspend fun updateCrono(crono:Cronos){
        cronosDatabaseDao.update(crono)
    }

    suspend fun deleteCrono(crono:Cronos) = cronosDatabaseDao.delete(crono)

    suspend fun getAllCronos():Flow<List<Cronos>> = cronosDatabaseDao.getCronos().flowOn(Dispatchers.IO)
        .conflate()

    suspend fun getCronosById(id:Long):Flow<Cronos> = cronosDatabaseDao.getCronosById(id).flowOn(Dispatchers.IO)
        .conflate()
}