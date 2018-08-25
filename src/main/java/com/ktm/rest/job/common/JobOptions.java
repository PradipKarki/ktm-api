package com.ktm.rest.job.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class JobOptions {
  private JobType jobType;
  private JobStep jobServiceProvider;
}
