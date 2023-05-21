create table if not exists StoreInfo
(
id SERIAL primary key,
store_id INTEGER Not NULL,
SKU INTEGER,
price INTEGER,
product_name varchar(255),
product_date Date
);

CREATE INDEX store_id_index
ON StoreInfo(store_id);
