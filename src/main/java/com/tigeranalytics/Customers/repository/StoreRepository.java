package com.tigeranalytics.Customers.repository;

import com.tigeranalytics.Customers.model.Storeinfo;
import com.tigeranalytics.Customers.service.JpaRepositoryInterface.StoreMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StoreRepository extends JpaRepository<Storeinfo, Long> {
    @Query(value = "Select s.* from StoreInfo s", nativeQuery = true)
    public List<StoreMapper> findAllStores();

    @Query(value = "select distinct s.* " +
            "    from storeinfo s " +
            "    where " +
            "         ( true = :isStore_id or s.store_id = :store_id) AND " +
            "         ( true = :isProduct_name or s.product_name LIKE %:product_name%) AND " +
            "         ( true = :isDate or s.product_date BETWEEN :from AND :to) " +
            "         order by s.id desc", nativeQuery = true)
    public List<StoreMapper> search(@Param("isStore_id") boolean isStore_id,
                                    @Param("store_id") long store_id,
                                    @Param("isProduct_name") boolean isProduct_name,
                                    @Param("product_name") String product_name,
                                    @Param("isDate") boolean isDate,
                                    @Param("from") Date from,
                                    @Param("to") Date to);

    @Query(value = "Update StoreInfo SET sku= :sku, " +
            "price = :price, " +
            "store_id = :store_id, " +
            "product_name = :product_name" +
            " where id = :id", nativeQuery = true)
    public void updateCustomer(@Param("id") long id,
                                           @Param("sku") long sku,
                                           @Param("price") long price,
                                           @Param("store_id") long store_id,
                                           @Param("product_name") String product_name
                                           );
}
