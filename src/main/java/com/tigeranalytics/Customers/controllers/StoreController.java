package com.tigeranalytics.Customers.controllers;

import com.tigeranalytics.Customers.mapper.StoreSearch;
import com.tigeranalytics.Customers.mapper.UpdateStore;
import com.tigeranalytics.Customers.service.JpaRepositoryInterface.StoreMapper;
import com.tigeranalytics.Customers.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StoreController {
    @Autowired
    private StoreService service;

    @GetMapping("/api/v1/fetchAll")
    public List<StoreMapper> fetchAll() {
        List<StoreMapper> info = service.fetchAll();
        return info;
    }

    @PostMapping("/api/v1/search")
    public List<StoreMapper> search(@RequestBody StoreSearch storeSearch) throws ParseException {
        return service.searchStore(storeSearch);
    }

    @PutMapping("/api/v1/UpdateStore")
    public void UpdateStore(@RequestBody UpdateStore updateStore) {
        service.updateStore(updateStore);
    }

    @DeleteMapping("/api/v1/deleteStoreProduct/{id}")
    public void deleteJobs(@PathVariable("id") Long id) {
        service.deleteStoreProduct(id);
    }

}

