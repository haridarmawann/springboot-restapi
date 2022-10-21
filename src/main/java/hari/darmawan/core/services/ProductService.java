package hari.darmawan.core.services;

import hari.darmawan.core.models.entities.Product;
import hari.darmawan.core.models.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired //dependecyInjecton
    private ProductRepo productRepo;

    public Product save(Product product){
        return productRepo.save(product);
    }

    public Product findOne(long id){
        Optional<Product> temp = productRepo.findById(id);
        if(!temp.isPresent()){
            return null;
        }
        return temp.get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(long id){
        productRepo.deleteById(id);
    }
//    public List<Product> findByName(String name){
//        return productRepo.FindByNameContains(name);
//    }
}
