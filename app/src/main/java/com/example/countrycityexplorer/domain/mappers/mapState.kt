package com.example.countrycityexplorer.domain.mappers


import com.example.countrycityexplorer.data.model.State as DataState
import com.example.countrycityexplorer.domain.model.State as DomainState

fun DataState.toDomainModel(): DomainState {
    return DomainState(
        name = this.name,
        iso2 = this.iso2,
        iso3 = this.iso3
    )
}
