package com.example.sync.version.domain

import com.example.sync.version.domain.vo.OsType
import com.example.sync.version.domain.vo.VersionResult

data class Version(
    private val os: OsType,
    private val osVer: String,
    private val appVer: String
) {

    fun getVersionStatus(versionChecker: VersionChecker): VersionResult {
        return versionChecker.getStatus(
            osVer = osVer,
            appVer = appVer
        )
    }

    companion object {
        fun of(
            os: OsType,
            osVer: String,
            appVer: String
        ) = Version(os, osVer, appVer)
    }
}
