plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kafka-study"
include("kafka-consumer")
include("kafka-producer")
include("exam-infrastructure")
