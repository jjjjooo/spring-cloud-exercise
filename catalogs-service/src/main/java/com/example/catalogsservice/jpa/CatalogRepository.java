package com.example.catalogsservice.jpa;


import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository  extends CrudRepository<CatalogEntity, Long> {
    CatalogEntity findByProductId(String productId);
}
