package com.ktm.rest.job.common;

public class JobLauncher {
  private JobLauncher() {}

  public static void run(Job job, String[] args) {
    job.processJob(args);
  }
}
