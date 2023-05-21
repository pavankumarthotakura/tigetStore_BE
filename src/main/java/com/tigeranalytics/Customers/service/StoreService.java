package com.tigeranalytics.Customers.service;

import com.tigeranalytics.Customers.mapper.StoreSearch;
import com.tigeranalytics.Customers.mapper.StoreSearchBuilder;
import com.tigeranalytics.Customers.mapper.UpdateStore;
import com.tigeranalytics.Customers.model.Storeinfo;
import com.tigeranalytics.Customers.repository.StoreRepository;
import com.tigeranalytics.Customers.service.JpaRepositoryInterface.StoreMapper;
import com.tigeranalytics.Customers.utils.ExcelHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Transactional
    public void importExcel(MultipartFile file) throws IOException {
        if (ExcelHelper.hasExcelFormat(file)) {
            List<Storeinfo> ls = ExcelHelper.excelToTutorials(file.getInputStream());
            storeRepository.saveAll(ls);
        }
    }

    @Transactional
    public void updateStore(UpdateStore updateStore) {
         Optional<Storeinfo> store = storeRepository.findById(updateStore.getId());
         if(!store.isPresent()) {
             throw new RuntimeException("value not found");
         }
         Storeinfo storeinfo = store.get();
         storeinfo.setStore_id(updateStore.getStore_id());
         storeinfo.setPrice(updateStore.getPrice());
         storeinfo.setSku(updateStore.getSku());
         storeinfo.setProduct_name(updateStore.getProduct_name());
         storeRepository.save(storeinfo);
    }
    public List<StoreMapper> fetchAll() {
        return storeRepository.findAllStores();
    }

    public void deleteStoreProduct(long id) {
        storeRepository.deleteById(id);
    }

    public List<StoreMapper> searchStore(StoreSearch storeSearch) throws ParseException {
        StoreSearchBuilder storeSearchBuilder = storeSearch.builder();

        return storeRepository.search(storeSearchBuilder.isStore_id(),
                storeSearchBuilder.getStore_id(),
                storeSearchBuilder.isProduct_name(),
                storeSearchBuilder.getProduct_name(),
                storeSearchBuilder.isDate(),
                storeSearchBuilder.getFrom(),
                storeSearchBuilder.getTo());
    }
}
