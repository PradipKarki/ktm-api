package com.ktm.library.core.mapper;

import com.ktm.library.core.model.directory.Directory;
import com.ktm.library.core.model.directory.DirectoryDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DirectoryMapper {

  List<Directory> toDirectory(List<DirectoryDto> directoryDto);

  @Mapping(target = "id", ignore = true)
  Directory toDirectory(DirectoryDto directoryDto);
}
