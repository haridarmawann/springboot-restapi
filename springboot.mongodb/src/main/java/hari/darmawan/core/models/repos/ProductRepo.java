package hari.darmawan.core.models.repos;

import hari.darmawan.core.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product,Long> {

//    List<Product> FindByNameContains(String name); //derivied query function
}
