package com.SistemaContable.commons;

import org.springframework.data.repository.Repository;


public interface GenericToData<T, ID> {

    T save (T entity);
}
