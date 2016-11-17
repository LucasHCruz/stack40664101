package com.cnova.mpschedule.core.service;

import com.cnova.mpschedule.core.dto.JobDetailDTO;

import java.util.List;

public interface JobService {
    List<JobDetailDTO> findJobs();

    void registerJob(JobDetailDTO job);

    void updateJob(JobDetailDTO job);

    void deleteJob(JobDetailDTO job);
}
