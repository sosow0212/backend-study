package com.example.sync.version.ui

import com.example.sync.version.application.VersionService
import com.example.sync.version.domain.vo.OsType
import com.example.sync.version.domain.vo.VersionResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/version")
class VersionController(
    private val versionService: VersionService,
) {

    @GetMapping("/check")
    fun check(
        @RequestParam os: OsType,
        @RequestParam osVer: String,
        @RequestParam appVer: String
    ): VersionResult = versionService.getVersionResult(os, osVer, appVer)
}
