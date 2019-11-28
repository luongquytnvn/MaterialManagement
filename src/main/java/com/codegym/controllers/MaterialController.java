package com.codegym.controllers;

import com.codegym.models.Material;
import com.codegym.models.Supplier;
import com.codegym.services.material.MaterialService;
import com.codegym.services.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private SupplierService supplierService;
    @GetMapping("/materials")
    public String materialList(Model model) {
        List<Material> materials = materialService.findAll();
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
    public List<Supplier> suppliers(){
        return supplierService.findAll();
    }
}
