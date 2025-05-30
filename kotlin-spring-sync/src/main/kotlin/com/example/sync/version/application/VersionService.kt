package com.example.sync.version.application

import com.example.sync.version.domain.Version
import com.example.sync.version.domain.vo.OsType
import com.example.sync.version.domain.vo.VersionResult
import org.springframework.stereotype.Service

@Service
class VersionService(
    private val versionStrategy: VersionStrategy
) {

    fun getVersionResult(os: OsType, osVer: String, appVer: String): VersionResult {
        val versionChecker = versionStrategy.getVersionChecker(os)

        return Version.of(
            os = os,
            osVer = osVer,
            appVer = appVer
        ).getVersionStatus(versionChecker = versionChecker)
    }
}
