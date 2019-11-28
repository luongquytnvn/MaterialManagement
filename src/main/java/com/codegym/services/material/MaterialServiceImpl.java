package com.codegym.services.material;

import com.codegym.models.Material;
import com.codegym.models.Supplier;
import com.codegym.repositorys.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<Material> findAll() {
        return (List<Material>) materialRepository.findAll();
    }

    @Override
    public List<Material> findAllBySupplier(Supplier supplier) {
        return (List<Material>) materialRepository.findAllBySupplier(supplier);
    }

    @Override
    public Material findById(Long id) {
        return materialRepository.findOne(id);
    }

    @Override
    public void save(Material material) {
        materialRepository.save(material);
    }

    @Override
    public void remove(Long id) {
        materialRepository.delete(id);
    }
}
