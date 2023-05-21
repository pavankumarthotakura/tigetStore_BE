package com.tigeranalytics.Customers.service.JpaRepositoryInterface;

import java.util.Date;

public interface StoreMapper {
    public long getId();

    public long getStore_id();

    public long getSku();

    public long getPrice();

    public String getProduct_name();

    public Date getProduct_date();
}
