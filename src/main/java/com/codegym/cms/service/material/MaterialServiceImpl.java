package com.codegym.cms.service.material;

import com.codegym.cms.model.Material;
import com.codegym.cms.model.Supplier;
import com.codegym.cms.repository.MaterialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Page<Material> findAll(Pageable pageable) {

        return materialRepository.findAll(pageable);
    }


    @Override
    public List<Material> findAllBySupplier(Supplier supplier) {
        return materialRepository.findAllBySupplier(supplier);
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