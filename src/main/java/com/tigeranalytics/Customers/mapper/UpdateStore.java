package com.tigeranalytics.Customers.mapper;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateStore {
    private long id;
    private long store_id;
    private long sku;
    private long price;
    private String product_name;
}
