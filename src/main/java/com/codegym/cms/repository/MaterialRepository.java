package com.codegym.cms.repository;

import com.codegym.cms.model.Material;
import com.codegym.cms.model.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MaterialRepository extends PagingAndSortingRepository<Material, Long> {
    List<Material> findAllBySupplier(Supplier supplier);
}
