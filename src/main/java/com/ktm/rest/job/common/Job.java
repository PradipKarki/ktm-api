package com.ktm.rest.job.common;

public interface Job {

  void init();

  JobConfiguration buildJobOptions();

  default void processJob(String[] args) {
    init();
    performSteps(args[0]);
  }

  void performSteps(String arg);
}
