package com.example.sync.version.infrastructure

import com.example.sync.version.application.VersionStrategy
import com.example.sync.version.domain.VersionChecker
import com.example.sync.version.domain.vo.OsType
import org.springframework.stereotype.Component

@Component
class VersionStrategyImpl(
    versionCheckers: Set<VersionChecker>
) : VersionStrategy {

    private val adapter: Map<OsType, VersionChecker> =
        versionCheckers.associateBy { it.isSupport() }

    override fun getVersionChecker(os: OsType): VersionChecker {
        return adapter[os]
            ?: throw IllegalArgumentException("No VersionChecker found for OsType: $os")
    }
}
