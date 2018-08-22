package com.ktm.rest.directory.mapper;

import com.ktm.rest.directory.model.Directory;
import com.ktm.rest.directory.model.DirectoryDto;
import java.util.List;
import org.mapstruct.Mapping;

public interface DirectoryMapper {

  List<Directory> toDirectory(List<DirectoryDto> directoryDto);

  @Mapping(target = "id", ignore = true)
  Directory toDirectory(DirectoryDto directoryDto);
}
