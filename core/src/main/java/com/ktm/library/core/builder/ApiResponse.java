package com.ktm.library.core.builder;

import java.util.List;

public interface ApiResponse<T> {

  List<T> getApiData(String queryString);
}
