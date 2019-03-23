package com.ktm.library.core.mapper;

import java.util.List;

public interface DtoConverter<I, O> {

  List<O> mapToDto(List<I> apiEntities);
}
