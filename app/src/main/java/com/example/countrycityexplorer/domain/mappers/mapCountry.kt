package com.example.countrycityexplorer.domain.mappers


import com.example.countrycityexplorer.data.model.Country as DataCountry
import com.example.countrycityexplorer.domain.model.Country as DomainCountry

fun DataCountry.toDomainModel(): DomainCountry {
    return DomainCountry(
        country = this.country,
        iso2 = this.iso2,
        iso3 = this.iso3
    )
}
