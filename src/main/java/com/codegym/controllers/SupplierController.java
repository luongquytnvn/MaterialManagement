package com.codegym.controllers;

import com.codegym.models.Material;
import com.codegym.models.Supplier;
import com.codegym.models.Supplier;
import com.codegym.services.supplier.SupplierService;
import com.codegym.services.supplier.SupplierService;
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
        return "suppliers/list";
    }
    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/create-supplier")
    public String createSupplier(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/create";
    }
    @PostMapping("/create-supplier")
    public String saveNewSupplier(Supplier supplier, Model model) {
        supplierService.save(supplier);
        model.addAttribute("message", "Added new supplier");
        model.addAttribute("supplier", new Supplier());
        return "suppliers/create";
    }
    @GetMapping("/edit-supplier/{id}")
    public String editSupplier(@PathVariable Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        return "suppliers/edit";
    }
    @PostMapping("/edit-supplier")
    public String saveEditSupplier(Model model, Supplier supplier) {
        supplierService.save(supplier);
        model.addAttribute("message","Saved");
        return "suppliers/edit";
    }
    @GetMapping("/delete-supplier/{id}")
    public String deleteSupplier(@PathVariable Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        return "suppliers/delete";
    }
    @PostMapping("/delete-supplier")
    public String saveDeleteSupplier(Supplier supplier) {
        supplierService.remove(supplier.getId());
        return "suppliers/delete";
    }

}
