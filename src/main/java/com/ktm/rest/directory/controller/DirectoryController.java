package com.ktm.rest.directory.controller;

import com.ktm.exception.ResourceNotFoundException;
import com.ktm.rest.ApiConstants;
import com.ktm.rest.directory.mapper.DirectoryMapper;
import com.ktm.rest.directory.model.Directory;
import com.ktm.rest.directory.model.DirectoryDto;
import com.ktm.rest.directory.service.DirectoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.mapstruct.factory.Mappers;
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
@Api(tags = ApiConstants.DIRECTORY, description = "KTM Times Directory CRUD operations")
public class DirectoryController {

  @Autowired private DirectoryService directoryService;

  @PostMapping
  @ApiOperation("Create a new directory")
  public Directory create(@Valid @RequestBody DirectoryDto directoryDto) {
    Directory directory = Mappers.getMapper(DirectoryMapper.class).toDirectory(directoryDto);
    return directoryService.create(directory);
  }

  @GetMapping("/{id}")
  @ApiOperation("Get a single directory by Id")
  public Directory read(@PathVariable Long id) {
    return directoryService.read(id).orElseThrow(ResourceNotFoundException::new);
  }

  @PutMapping("/{id}")
  @ApiOperation("Update a directory by Id")
  public Directory update(@PathVariable Long id, @Valid @RequestBody DirectoryDto directoryDto) {
    Directory directory = Mappers.getMapper(DirectoryMapper.class).toDirectory(directoryDto);
    Directory updatedDirectory =
        directoryService.read(id).orElseThrow(ResourceNotFoundException::new);
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
    Directory directory = directoryService.read(id).orElseThrow(ResourceNotFoundException::new);
    directoryService.delete(directory);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/All")
  @ApiOperation("Create a batch of directories")
  public List<Directory> createAll(@Valid @RequestBody List<Directory> directories) {
    return directoryService.createAll(directories);
  }

  @GetMapping
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Get a list of all directories")
  public List<Directory> retrieveAll() {
    return directoryService.retrieveAll();
  }

  @PutMapping
  @ApiOperation("Update a list of directories with a given list of directories")
  public List<Directory> updateAll(List<Directory> directories) {
    return directoryService.updateAll(directories);
  }

  @DeleteMapping
  @ApiOperation("Delete a list of directories with a given list of directories")
  public void deleteAll(List<Directory> directories) {
    directoryService.deleteAll(directories);
  }
}
