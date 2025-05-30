package com.example.sync.version

import com.example.sync.version.domain.vo.OsType
import com.example.sync.version.domain.vo.VersionResult
import com.example.sync.version.ui.VersionController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VersionApplicationTests {

    @Autowired
    lateinit var controller: VersionController

    @Nested
    inner class IosTest {

        @Test
        fun `iOS - 강제 업데이트 - 5_0_0 미만`() {
            assertEquals(VersionResult.FORCE_UPDATE, controller.check(OsType.IOS, "13.0", "4.9.9"))
        }

        @Test
        fun `iOS - 강제 업데이트 - 5_42_2`() {
            assertEquals(VersionResult.FORCE_UPDATE, controller.check(OsType.IOS, "15.0", "5.42.2"))
        }

        @Test
        fun `iOS - 업데이트 필요 - os 13 미만, 앱 5_28_0 미만`() {
            assertEquals(VersionResult.UPDATE, controller.check(OsType.IOS, "12.4", "5.20.0"))
        }

        @Test
        fun `iOS - 정상 - os 13 미만, 앱 5_28_0 이상`() {
            assertEquals(VersionResult.OK, controller.check(OsType.IOS, "12.4", "5.28.0"))
        }

        @Test
        fun `iOS - 업데이트 필요 - os 13 이상, 앱 5_100_0 미만`() {
            assertEquals(VersionResult.UPDATE, controller.check(OsType.IOS, "15.0", "5.90.0"))
        }

        @Test
        fun `iOS - 정상 - os 13 이상, 앱 5_100_0 이상`() {
            assertEquals(VersionResult.OK, controller.check(OsType.IOS, "15.0", "5.100.0"))
        }
    }

    @Nested
    inner class AosTest {

        @Test
        fun `Android - 강제 업데이트 - 5_0_0 미만`() {
            assertEquals(VersionResult.FORCE_UPDATE, controller.check(OsType.ANDROID, "6.0", "4.9.9"))
        }

        @Test
        fun `Android - 업데이트 필요 - os 6 미만, 앱 5_31_1 미만`() {
            assertEquals(VersionResult.UPDATE, controller.check(OsType.ANDROID, "5.1", "5.20.0"))
        }

        @Test
        fun `Android - 정상 - os 6 미만, 앱 5_31_1 이상`() {
            assertEquals(VersionResult.OK, controller.check(OsType.ANDROID, "5.1", "5.31.1"))
        }

        @Test
        fun `Android - 업데이트 필요 - os 6 이상, 앱 5_100_0 미만`() {
            assertEquals(VersionResult.UPDATE, controller.check(OsType.ANDROID, "10.0", "5.90.0"))
        }

        @Test
        fun `Android - 정상 - os 6 이상, 앱 5_100_0 이상`() {
            assertEquals(VersionResult.OK, controller.check(OsType.ANDROID, "10.0", "5.100.0"))
        }
    }
}
