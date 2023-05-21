package com.tigeranalytics.Customers.mapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class StoreSearchBuilder {
    long store_id;
    boolean isStore_id;
    String product_name;
    boolean isProduct_name;
    Date from;
    Date to;
    boolean isDate;

    public boolean isStore_id() {
        return isStore_id;
    }

    public boolean isProduct_name() {
        return isProduct_name;
    }

    public boolean isDate() {
        return isDate;
    }
}
