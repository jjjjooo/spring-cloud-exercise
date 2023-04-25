package com.example.catalogsservice.controller;

import com.example.catalogsservice.jpa.CatalogEntity;
import com.example.catalogsservice.service.CatalogService;
import com.example.catalogsservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalogs-service")
public class CatalogController {
    private final Environment env;
    private final CatalogService catalogService;
    @GetMapping("/health_check")
    public String status(){
        return String.format("catalogs-service health check on Port %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs(){
        Iterable<CatalogEntity> catalogEntities = catalogService.getAllCatalogs();
        List<ResponseCatalog> responseCatalogs = new ArrayList<>();
        catalogEntities.forEach(c -> {
            responseCatalogs.add(new ModelMapper().map(c, ResponseCatalog.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(responseCatalogs);
    }

}
