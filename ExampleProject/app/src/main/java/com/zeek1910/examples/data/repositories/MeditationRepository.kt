package com.zeek1910.examples.data.repositories

import com.zeek1910.examples.models.MeditationItem

interface MeditationRepository {

    suspend fun getData(): List<MeditationItem>
}