package com.example.catalogsservice.message;

import com.example.catalogsservice.jpa.CatalogEntity;
import com.example.catalogsservice.jpa.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class KafkaConsumer {
    private CatalogRepository catalogRepository;

    @Autowired
    public KafkaConsumer(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String kafkaMessage){
        log.info("Kafka message: ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            map = objectMapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException ex){
            ex.printStackTrace();
        }

        CatalogEntity catalogEntity= catalogRepository.findByProductId((String) map.get("productId"));
        if(catalogEntity != null){
            catalogEntity.setStock(catalogEntity.getStock()- (Integer) map.get("qty"));
            catalogRepository.save(catalogEntity);
        }
    }
}
