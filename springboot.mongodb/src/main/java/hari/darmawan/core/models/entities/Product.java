package hari.darmawan.core.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tbl_product") //untuk mengecek apakah ada tabel product kalo gada akan di generate
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name",length = 100)
    private String nama;

    @Column(name = "product_description",length =500)
    private String description;

    private double price;

    public Product(Long id, String nama, String description, double price) {
        this.id = id;
        this.nama = nama;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
