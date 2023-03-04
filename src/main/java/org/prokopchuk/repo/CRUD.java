package org.prokopchuk.repo;

import org.prokopchuk.Company;

import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CRUD<T> {
    int insert(T object);
    List<T> readAll();
    Optional<T> readById(int id);
    int updateById(int id);
    void deleteById(int id);

}
