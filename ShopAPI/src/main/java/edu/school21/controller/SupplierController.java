package edu.school21.controller;


import edu.school21.model.Supplier;
import edu.school21.model.Address;
import edu.school21.service.SupplierService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

  private final SupplierService supplierService;

  @Autowired
  public SupplierController(SupplierService supplierService) {
    this.supplierService = supplierService;
  }

  @PostMapping
  public void createSupplier(@RequestBody Supplier supplier) {
    supplierService.addSupplier(supplier);
  }

  @PatchMapping("/{id}")
  public void updateSupplier(@PathVariable UUID id, @RequestBody Address address) {
    supplierService.updateSupplier(id, address);
  }

  @DeleteMapping("/{id}")
  public void deleteSupplier(@PathVariable UUID id) {
    supplierService.deleteSupplier(id);
  }

  @GetMapping()
  public List<Supplier> getAllSupplier() {
    return supplierService.getAllSuppliers();
  }

  @GetMapping("/{id}")
  public Supplier getSupplier(@PathVariable UUID id) {
    return supplierService.getSupplier(id);
  }

}
