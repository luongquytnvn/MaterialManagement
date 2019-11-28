package com.codegym.repositorys;

import com.codegym.models.Material;
import com.codegym.models.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MaterialRepository extends PagingAndSortingRepository<Material,Long> {
    List<Material> findAllBySupplier(Supplier supplier);
}
