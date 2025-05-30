package com.example.sync.version.infrastructure.oschecker

import com.example.sync.version.application.VersionUtils
import com.example.sync.version.domain.VersionChecker
import com.example.sync.version.domain.vo.OsType
import com.example.sync.version.domain.vo.VersionResult
import org.springframework.stereotype.Component

@Component
class IosChecker : VersionChecker {

    override fun isSupport() = OsType.IOS

    override fun getStatus(osVer: String, appVer: String): VersionResult {
        // 앱 버전 5.0 미만 또는 5.42.2 -> 강제 업데이트
        if (VersionUtils.compare(appVer, "5.0.0") < 0 || appVer == "5.42.2") {
            return VersionResult.FORCE_UPDATE
        }

        val isOsBelow13 = VersionUtils.compare(osVer, "13.0") < 0
        val isAppBelow528 = VersionUtils.compare(appVer, "5.28.0") < 0
        val isAppBelow5100 = VersionUtils.compare(appVer, "5.100.0") < 0

        return when {
            // OS 13 미만 & 앱 < 5.28.0 == 업데이트
            isOsBelow13 && isAppBelow528 -> VersionResult.UPDATE

            // OS 13 미만 & 앱 ≥ 5.28.0 == OK
            isOsBelow13 -> VersionResult.OK

            // OS 13 이상 & 앱 < 5.100.0 == 업데이트
            !isOsBelow13 && isAppBelow5100 -> VersionResult.UPDATE

            else -> VersionResult.OK
        }
    }
}

