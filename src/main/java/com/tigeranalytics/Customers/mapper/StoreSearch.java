package com.tigeranalytics.Customers.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Setter
@Getter
public class StoreSearch {

    long store_id;
    String product_name;
    Date from;
    Date to;

    @JsonIgnore
    @Transient
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public StoreSearchBuilder builder() throws ParseException {
        return StoreSearchBuilder
                .builder()
                .isProduct_name(checkForString(this.getProduct_name()))
                .product_name(Objects.isNull(this.getProduct_name()) ? "" :this.getProduct_name())
                .isStore_id(Objects.isNull(this.getStore_id()) || this.getStore_id() == 0)
                .store_id(this.getStore_id())
                .isDate(Objects.isNull(this.getFrom()) && Objects.isNull(this.getTo()))
                .from(Objects.isNull(this.getFrom()) ?
                        formatter.parse("0000-00-00") : this.getFrom())
                .to(Objects.isNull(this.getTo()) ?
                        formatter.parse("0000-00-00") : this.getTo())
                .build();
    }

    private boolean checkForString(String name) {
        return Objects.isNull(name) || name.trim().isEmpty();
    }
}
