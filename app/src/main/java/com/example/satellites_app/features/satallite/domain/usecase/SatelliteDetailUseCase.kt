package com.example.satellites_app.features.satallite.domain.usecase

import com.example.satellites_app.features.satallite.domain.repository.SatelliteRepository
import javax.inject.Inject

class SatelliteDetailUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    suspend operator fun invoke(id: Int) = satelliteRepository.getSatelliteDetails(id)

}