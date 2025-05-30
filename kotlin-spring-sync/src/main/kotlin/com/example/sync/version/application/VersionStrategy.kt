package com.example.sync.version.application

import com.example.sync.version.domain.VersionChecker
import com.example.sync.version.domain.vo.OsType

interface VersionStrategy {

    fun getVersionChecker(os: OsType) : VersionChecker
}
