package com.example.sync.version.domain

import com.example.sync.version.domain.vo.OsType
import com.example.sync.version.domain.vo.VersionResult

interface VersionChecker {

    fun isSupport(): OsType

    fun getStatus(osVer: String, appVer: String): VersionResult
}
