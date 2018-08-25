package com.ktm.rest.job.common;

import com.ktm.rest.BaseEntity;
import java.util.List;

/**
 * JobStep interface defines multiple steps to perform the job.
 *
 * <p>JobService interface extends JobStep.
 *
 * <p>JobServiceImpl implements JobService: steps to perform the job.
 *
 * <p>JobLauncher launches the job with Job and args as parameters.
 *
 * <p>JobType Enum defines Job types like Twitter, YouTube. It follows the naming convention such
 * as: TwitterApiJob to TWITTER_API_JOB, YouTubeApiJob to YOUTUBE_API_JOB
 *
 * <p>Job abstract class provides an abstract method <code>buildJobOptions()</code> to be
 * implemented by derived Job classes.
 *
 * <p>JobOptions class hold job specific configuration information like service provider, job type.
 * JobOptions has one public method <code>build()</code> to set these informtion. Derived Job
 * classes set these values in JobOptions prior running job. Derived Job classes have specific
 * service provider - implementation of JobStep, which will be set in JobOptions.
 */
public abstract class JobImpl<T, E extends BaseEntity> implements Job {
  private JobType jobType;
  private JobStep<T, E> jobServiceProvider;

  @Override
  public void init() {
    JobOptions jobOptions = buildJobOptions();
    jobType = jobOptions.getJobType();
    //JobServiceProvider will always be instance of JobStep
    //noinspection unchecked
    jobServiceProvider = jobOptions.getJobServiceProvider();
  }

  @Override
  public void performSteps(String arg) {
    List<T> apiEntities = jobServiceProvider.getDataFromAPI(arg);
    List<E> domainEntities = jobServiceProvider.mapToDomainObjects(jobType, apiEntities);
    List<E> filteredEntities = jobServiceProvider.processData(domainEntities);
    jobServiceProvider.saveToDB(filteredEntities);
  }
}
