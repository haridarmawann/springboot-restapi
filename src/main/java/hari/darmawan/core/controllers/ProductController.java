package hari.darmawan.core.controllers;

import hari.darmawan.core.dto.ResponseBody;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<ResponseBody> getAllData() {
        try {
            List<Product> products = new ArrayList<>();
            products.addAll(productService.findAll());

            if (products.isEmpty()){
                ResponseBody response = new ResponseBody(204,"Data is empty",null);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            ResponseBody response = new ResponseBody(200,"Data Success Retrieved",products);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            ResponseBody response = new ResponseBody(500,e.getMessage(),null);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> findById(@PathVariable("id") String id){
        try {
            Optional<Product> product = productService.findOne(id);

            if (product.isPresent()) {
                ResponseBody response = new ResponseBody(200, "Data Succes Retrived", product);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            ResponseBody responseBody = new ResponseBody(204, "Data is empty", null);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
        catch (Exception e){
            ResponseBody responseBody = new ResponseBody(500, e.getMessage(),null);
            return new ResponseEntity<>(responseBody,HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }


    @PutMapping
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
