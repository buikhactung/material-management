package com.codegym.cms.controller;

import com.codegym.cms.model.Supplier;
import com.codegym.cms.model.Material;
import com.codegym.cms.service.supplier.SupplierService;
import com.codegym.cms.service.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @GetMapping("/suppliers")
    public String supplierList(Model model,@PageableDefault(size = 5) Pageable pageable){
        Page<Supplier> suppliers = supplierService.findAll(pageable);
        model.addAttribute("suppliers",suppliers);
        return "supplier/list";
    }
//    @GetMapping("/")
//    public String homePage(){
//        return "home";
//    }
    @GetMapping("/create-supplier")
    public String createSupplier(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier/create";
    }
    @PostMapping("/create-supplier")
    public String saveNewSupplier(Supplier supplier, Model model) {
        supplierService.save(supplier);
        model.addAttribute("message", "Added new supplier");
        model.addAttribute("supplier", new Supplier());
        return "supplier/create";
    }
    @GetMapping("/edit-supplier/{id}")
    public String editSupplier(@PathVariable Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        return "supplier/edit";
    }
    @PostMapping("/edit-supplier")
    public String saveEditSupplier(Model model, Supplier supplier) {
        supplierService.save(supplier);
        model.addAttribute("message","Saved");
        return "supplier/edit";
    }
    @GetMapping("/delete-supplier/{id}")
    public String deleteSupplier(@PathVariable Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        return "supplier/delete";
    }
    @PostMapping("/delete-supplier")
    public String saveDeleteSupplier(Supplier supplier) {
        supplierService.remove(supplier.getId());
        return "supplier/delete";
    }

}