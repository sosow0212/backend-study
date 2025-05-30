package com.example.sync.version.infrastructure.oschecker

import com.example.sync.version.application.VersionUtils
import com.example.sync.version.domain.VersionChecker
import com.example.sync.version.domain.vo.OsType
import com.example.sync.version.domain.vo.VersionResult
import org.springframework.stereotype.Component

@Component
class AndroidChecker : VersionChecker {

    override fun isSupport() = OsType.ANDROID

    override fun getStatus(osVer: String, appVer: String): VersionResult {
        // 앱 버전 5.0 미만 == 강제 업데이트
        if (VersionUtils.compare(appVer, "5.0.0") < 0) {
            return VersionResult.FORCE_UPDATE
        }

        val isOsBelow6 = VersionUtils.compare(osVer, "6.0") < 0
        val isAppBelow5311 = VersionUtils.compare(appVer, "5.31.1") < 0
        val isAppBelow5100 = VersionUtils.compare(appVer, "5.100.0") < 0

        return when {
            // OS 6 미만 & 앱 < 5.31.1 == 업데이트
            isOsBelow6 && isAppBelow5311 -> VersionResult.UPDATE

            // OS 6 미만 & 앱 ≥ 5.31.1 == OK
            isOsBelow6 -> VersionResult.OK

            // OS 6 이상 & 앱 < 5.100.0 == 업데이트
            !isOsBelow6 && isAppBelow5100 -> VersionResult.UPDATE

            else -> VersionResult.OK
        }
    }
}
