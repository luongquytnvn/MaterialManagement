package com.codegym.services.supplier;

import com.codegym.models.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> findAll();

    Supplier findById(Long id);

    void save(Supplier supplier);

    void remove(Long id);
}
