package com.ktm.rest.directory.service;

import com.ktm.rest.CrudCollectionService;
import com.ktm.rest.CrudService;
import com.ktm.rest.directory.model.Directory;

public interface DirectoryService
    extends CrudService<Long, Directory>, CrudCollectionService<Directory> {}
