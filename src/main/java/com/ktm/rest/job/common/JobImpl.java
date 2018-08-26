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
 * as: TwitterApiJob to TWITTER_API_JOB, YouTubeApiJob to YOUTUBE_API_JOB. It holds the value of
 * class name of Job which is used to check <code>instanceof</code> to get JobType.
 *
 * <p>JobImpl abstract class implements Job interface and doesn't implement <code>buildJobConfiguration()
 * </code> so that each specific job implements JobConfiguration and set service provider. Job
 * interface implements <code>processJob()</code> method and has 3 abstract methods. JobImpl
 * implements two methods and doesn't implement <code>buildJobConfiguration()</code>.
 *
 * <p>JobConfiguration class hold job specific configuration information like service provider.
 * JobConfiguration has one public method <code>build()</code> to set these information. Derived Job
 * classes set these values in JobConfiguration prior running job. Derived Job classes have specific
 * service provider - implementation of JobStep, which will be set in JobConfiguration.
 */
public abstract class JobImpl<T, E extends BaseEntity> implements Job {
  private JobStep<T, E> jobServiceProvider;

  @Override
  public void init() {
    JobConfiguration jobConfiguration = buildJobConfiguration();
    // JobServiceProvider will always be instance of JobStep
    //noinspection unchecked
    jobServiceProvider = jobConfiguration.getJobServiceProvider();
  }

  @Override
  public void performSteps(String arg) {
    List<T> apiEntities = jobServiceProvider.getDataFromAPI(arg);
    JobType jobType = JobType.getJobTypeFromClassName(this.getClass());
    List<E> domainEntities = jobServiceProvider.mapToDomainObjects(jobType, apiEntities);
    List<E> filteredEntities = jobServiceProvider.processData(domainEntities);
    jobServiceProvider.saveToDB(filteredEntities);
  }
}
