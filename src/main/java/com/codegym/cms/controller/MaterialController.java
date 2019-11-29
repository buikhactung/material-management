package com.codegym.cms.controller;

import com.codegym.cms.model.Material;
import com.codegym.cms.model.Supplier;
import com.codegym.cms.service.material.MaterialService;
import com.codegym.cms.service.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller

public class MaterialController {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/materials")
    public String materialList(Model model,@PageableDefault(size = 5,sort = "name") Pageable pageable) {
        Page<Material> materials = materialService.findAll(pageable);
        model.addAttribute("materials", materials);
        return "material/list";
    }

    @GetMapping("/create-material")
    public String createMaterial(Model model) {
        model.addAttribute("material", new Material());
        return "material/create";
    }

    @PostMapping("/create-material")
    public String saveNewMaterial(Material material, Model model) {
        materialService.save(material);
        model.addAttribute("message", "Added new material");
        model.addAttribute("material", new Material());
        return "material/create";
    }

    @GetMapping("/edit-material/{id}")
    public String editMaterial(@PathVariable Long id, Model model) {
        Material material = materialService.findById(id);
        model.addAttribute("material", material);
        return "material/edit";
    }

    @PostMapping("/edit-material")
    public String saveEditMaterial(Model model, Material material) {
        materialService.save(material);
        model.addAttribute("message", "Saved");
        return "material/edit";
    }

    @GetMapping("/delete-material/{id}")
    public String deleteMaterial(@PathVariable Long id, Model model) {
        Material material = materialService.findById(id);
        model.addAttribute("material", material);
        return "material/delete";
    }

    @PostMapping("/delete-material")
    public String saveDeleteMaterial(Material material) {
        materialService.remove(material.getId());
        return "material/delete";
    }

    @ModelAttribute("suppliers")
    public Page<Supplier> suppliers(Pageable pageable){
        return supplierService.findAll(pageable);
    }

    @GetMapping("/list-materrials/{id}")
    public String listMaterials(@PathVariable Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        List<Material> materials = materialService.findAllBySupplier(supplier);
        model.addAttribute("materials", materials);
        return "supplier/listmaterials-suppliers";
    }
    @GetMapping("/search-material")
    public String search(@RequestParam String search, Model model, @PageableDefault(size = 5,sort = "price") Pageable pageable){
        Page<Material> materials = materialService.findAll(pageable);
        List<Material> results = new ArrayList<>();
        for (Material material: materials) {
            if (material.getName().toUpperCase().contains(search.toUpperCase())) {
                results.add(material);
            }
        }
        model.addAttribute("materials",results);
        return "material/list";
    }
}

