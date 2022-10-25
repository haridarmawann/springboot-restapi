package hari.darmawan.core.models.repos;

import hari.darmawan.core.models.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface ProductRepo extends MongoRepository<Product,String> {

//    List<Product> FindByNameContains(String name);
}
