package com.ktm.library.core.service;

import com.ktm.library.core.model.Documentary;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DocumentaryFeedSanitizer implements Sanitizer<Documentary> {

  @Override
  public List<Documentary> processData(List<Documentary> documentaries) {
    return Collections.unmodifiableList(documentaries);
  }
}
