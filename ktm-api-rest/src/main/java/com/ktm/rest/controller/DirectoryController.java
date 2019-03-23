package com.ktm.rest.controller;

import static com.ktm.rest.ApiConstants.DIRECTORY;
import static org.mapstruct.factory.Mappers.getMapper;

import com.ktm.library.core.exception.ResourceNotFoundException;
import com.ktm.library.core.mapper.DirectoryMapper;
import com.ktm.library.core.model.directory.Directory;
import com.ktm.library.core.model.directory.DirectoryDto;
import com.ktm.library.core.service.DirectoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/directory")
@RefreshScope
@Api(tags = DIRECTORY, value = DIRECTORY)
public class DirectoryController {

  @Autowired private DirectoryService directoryService;

  @PostMapping
  @ApiOperation("Create a new directory")
  public Directory create(@Valid @RequestBody DirectoryDto directoryDto) {
    Directory directory = getMapper(DirectoryMapper.class).toDirectory(directoryDto);
    return directoryService.create(directory);
  }

  @GetMapping("/{id}")
  @ApiOperation("Get a single directory by Id")
  public Directory read(@PathVariable Long id) {
    return directoryService.find(id).orElseThrow(ResourceNotFoundException::new);
  }

  @PutMapping("/{id}")
  @ApiOperation("Update a directory by Id")
  public Directory update(@PathVariable Long id, @Valid @RequestBody DirectoryDto directoryDto) {
    Directory directory = getMapper(DirectoryMapper.class).toDirectory(directoryDto);
    Directory updatedDirectory =
        directoryService.find(id).orElseThrow(ResourceNotFoundException::new);
    updatedDirectory.setBusinessCategory(directory.getBusinessCategory());
    updatedDirectory.setDescription(directory.getDescription());
    updatedDirectory.setFax(directory.getFax());
    updatedDirectory.setIsVerified(directory.getIsVerified());
    updatedDirectory.setName(directory.getName());
    updatedDirectory.setWebsite(directory.getWebsite());
    updatedDirectory.setPhoneNumbers(directory.getPhoneNumbers());
    updatedDirectory.setEmailAddresses(directory.getEmailAddresses());
    updatedDirectory.setAddresses(directory.getAddresses());
    updatedDirectory.setContactPersons(directory.getContactPersons());
    updatedDirectory.setSocialMediaUrl(directory.getSocialMediaUrl());
    return directoryService.update(updatedDirectory);
  }

  @DeleteMapping("/{id}")
  @ApiOperation("Delete a directory by Id")
  public ResponseEntity<Directory> delete(@PathVariable Long id) {
    Directory directory = directoryService.find(id).orElseThrow(ResourceNotFoundException::new);
    directoryService.delete(directory);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/All")
  @ApiOperation("Create a batch of directories")
  public Iterable<Directory> createAll(@Valid @RequestBody List<Directory> directories) {
    return directoryService.saveAll(directories);
  }

  @GetMapping
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Get a list of all directories")
  public Iterable<Directory> retrieveAll() {
    return directoryService.findAll();
  }

  @PutMapping
  @ApiOperation("Update a list of directories with a given list of directories")
  public Iterable<Directory> updateAll(List<Directory> directories) {
    return directoryService.saveAll(directories);
  }

  @DeleteMapping
  @ApiOperation("Delete a list of directories with a given list of directories")
  public void deleteAll(List<Directory> directories) {
    directoryService.deleteAll(directories);
  }
}
