package hari.darmawan.core.controllers;

import hari.darmawan.core.dto.ResponseData;
import hari.darmawan.core.models.entities.Product;
import hari.darmawan.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired //controller memanggil service , service memanggil repositori
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayLoad(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayLoad(productService.save(product)); //disini masi bisa menjadi masalah saat save
        return ResponseEntity.ok(responseData);
    }

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public Product findOne(@PathVariable("id") String id){ // path variable untuk menyambungkan ke id yang di getmapping
        return productService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product,Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayLoad(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayLoad(productService.save(product));
        //disini masi bisa menjadi masalah saat save
        return ResponseEntity.ok(responseData);
    }

    @RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
    public void removeOne(@PathVariable("id") String id){ // path variable untuk menyambungkan ke id yang di getmapping
        productService.removeOne(id);
    }



}
