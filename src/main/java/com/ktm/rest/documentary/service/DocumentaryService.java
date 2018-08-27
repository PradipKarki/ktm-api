package com.ktm.rest.documentary.service;

import com.ktm.rest.CrudCollectionService;
import com.ktm.rest.CrudService;
import com.ktm.rest.documentary.model.Documentary;

public interface DocumentaryService
    extends CrudService<String, Documentary>, CrudCollectionService<Documentary> {}
