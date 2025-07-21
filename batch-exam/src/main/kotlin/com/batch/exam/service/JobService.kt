package com.batch.exam.service

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.stereotype.Service

@Service
class JobService(
    private val jobLauncher: JobLauncher,
    private val userProcessJob: Job
) {

    fun run() {
        val jobParameters = JobParametersBuilder()
            .addString("migrationName", "USER")
            .toJobParameters()

        jobLauncher.run(userProcessJob, jobParameters)
    }
}
