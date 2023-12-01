package com.example.satellites_app.features.satallite.domain.usecase

import com.example.satellites_app.features.satallite.domain.repository.SatelliteRepository
import javax.inject.Inject

class SatellitePositionsUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    suspend operator fun invoke(id: String) = satelliteRepository.getSatellitePositions(id)

}