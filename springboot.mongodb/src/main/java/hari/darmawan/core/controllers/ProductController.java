package hari.darmawan.core.controllers;

import hari.darmawan.core.models.entities.Product;
import hari.darmawan.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired //controller memanggil service , service memanggil repositori
    private ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.save(product);

    }

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public Product findOne(@PathVariable("id") Long id){ // path variable untuk menyambungkan ke id yang di getmapping
        return productService.findOne(id);
    }

//    @RequestMapping(method = RequestMethod.PUT)
//    public Product update(@RequestBody Product product) {
//        return productService.save(product);
//    }

    @RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public void removeOne(@PathVariable("id") Long id){ // path variable untuk menyambungkan ke id yang di getmapping
        productService.removeOne(id);
    }



}
