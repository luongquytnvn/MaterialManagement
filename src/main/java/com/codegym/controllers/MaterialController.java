package com.codegym.controllers;

import com.codegym.models.Material;
import com.codegym.models.Supplier;
import com.codegym.services.material.MaterialService;
import com.codegym.services.supplier.SupplierService;
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
    public String materialList(Model model,@PageableDefault(size = 5,sort = "price") Pageable pageable) {
        Page<Material> materials = materialService.findAll(pageable);
        model.addAttribute("materials", materials);
        return "materials/list";
    }
    @GetMapping("/create-material")
    public String createMaterial(Model model) {
        model.addAttribute("material", new Material());
        return "materials/create";
    }
    @PostMapping("/create-material")
    public String saveNewMaterial(Material material, Model model) {
        materialService.save(material);
        model.addAttribute("message", "Added new material");
        model.addAttribute("material", new Material());
        return "materials/create";
    }
    @GetMapping("/edit-material/{id}")
    public String editMaterial(@PathVariable Long id, Model model) {
        Material material = materialService.findById(id);
        model.addAttribute("material", material);
        return "materials/edit";
    }
    @PostMapping("/edit-material")
    public String saveEditMaterial(Model model, Material material) {
        materialService.save(material);
        model.addAttribute("message","Saved");
        return "materials/edit";
    }
    @GetMapping("/delete-material/{id}")
    public String deleteMaterial(@PathVariable Long id, Model model) {
        Material material = materialService.findById(id);
        model.addAttribute("material", material);
        return "materials/delete";
    }
    @PostMapping("/delete-material")
    public String saveDeleteMaterial(Material material) {
        materialService.remove(material.getId());
        return "materials/delete";
    }
    @ModelAttribute("suppliers")
    public Page<Supplier> suppliers(Pageable pageable){
        return supplierService.findAll(pageable);
    }
    @GetMapping("/list-materials/{id}")
    public String listMaterials(@PathVariable Long id, Model model){
        Supplier supplier = supplierService.findById(id);
        List<Material> materials = materialService.findAllBySupplier(supplier);
        model.addAttribute("materials", materials);
        return "suppliers/listMaterials-suppliers";
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
        return "materials/list";
    }
}
