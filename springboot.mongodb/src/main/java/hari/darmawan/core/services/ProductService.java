package hari.darmawan.core.services;

import hari.darmawan.core.models.entities.Product;
import hari.darmawan.core.models.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired //dependecyInjecton
    private ProductRepo productRepo;

    public Product create(Product product){
        return productRepo.save(product);
    }

    public Product findOne(long id){
        return productRepo.findById(id).get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(long id){
        productRepo.deleteById(id);
    }
}
