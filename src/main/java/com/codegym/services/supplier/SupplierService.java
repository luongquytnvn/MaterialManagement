package com.codegym.services.supplier;

import com.codegym.models.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplierService {
    Page<Supplier> findAll(Pageable pageable);

    Supplier findById(Long id);

    void save(Supplier supplier);

    void remove(Long id);
}
