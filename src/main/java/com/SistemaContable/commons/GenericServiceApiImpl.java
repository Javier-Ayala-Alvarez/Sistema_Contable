package com.SistemaContable.commons;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class GenericServiceApiImpl<T, ID extends Serializable>
        implements GenericServiceApi<T, ID>{


    public abstract CrudRepository<T, ID> getDao();

    @Override
    public T save(T entity) {
        return getDao().save(entity);
    }

    @Override
    public void delete(ID id) {
        getDao().deleteById(id);
    }

    @Override
    public T get(ID id) {
        Optional<T> obj = getDao().findById(id);
        return obj.orElse(null);
    }

    @Override
    public List getAll() {
        List<T> listObject = new ArrayList<>();
        getDao().findAll().forEach(listObject::add);
        return listObject;
    }

}
