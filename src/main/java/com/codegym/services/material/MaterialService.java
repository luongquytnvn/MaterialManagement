package com.codegym.services.material;

import com.codegym.models.Material;
import com.codegym.models.Supplier;

import java.util.List;

public interface MaterialService {
    List<Material> findAll();
    List<Material> findAllBySupplier(Supplier supplier);

    Material findById(Long id);

    void save(Material material);

    void remove(Long id);
}
