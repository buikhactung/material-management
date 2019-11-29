package com.codegym.cms.service.material;
import com.codegym.cms.model.Material;
import com.codegym.cms.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MaterialService {
    Page<Material> findAll(Pageable pageable);
    List<Material> findAllBySupplier(Supplier supplier);
    Material findById(Long id);
    void save(Material material);

    void remove(Long id);
}
