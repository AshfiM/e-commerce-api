package my.ecommerce.models;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class ProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column
    private String product_name;
    @Column
    private float price;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    CategoryEntity category;

    public ProductsEntity(){}
    public ProductsEntity(String product_name, float price) {
        this.product_name = product_name;
        this.price = price;
    }
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }


    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }



}
