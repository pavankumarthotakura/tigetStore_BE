package com.tigeranalytics.Customers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "storeinfo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Storeinfo implements Serializable {
    private static final long serialVersionUID = -1778699323343839384L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "store_id", nullable = false)
    private long store_id;
    @Column(name = "sku", nullable = false)
    private long sku;
    @Column(name = "price", nullable = false)
    private long price;
    @Column(name = "product_name", nullable = false)
    private String product_name;
    private Date product_date;
}
