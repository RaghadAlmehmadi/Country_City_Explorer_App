package com.example.countrycityexplorer.domain.mappers

import com.example.countrycityexplorer.data.model.City as DataCity
import com.example.countrycityexplorer.domain.model.City as DomainCity

fun DataCity.toDomainModel(): DomainCity {
    return DomainCity(
        cityname = this.cityname
    )
}
