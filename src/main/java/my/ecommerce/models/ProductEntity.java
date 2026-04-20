package my.ecommerce.models;

import jakarta.persistence.*;
import my.ecommerce.dto.ProductDto;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column
    private String product_name;
    @Column
    private float price;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    CategoryEntity category;

    public ProductEntity(){}
    public ProductEntity(String product_name, float price) {
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


    public Long getId() {
        return id;
    }

    public void setId(Long product_id) {
        this.id = product_id;
    }

    public ProductDto productEntityToProductDto() {
        return new ProductDto(this.id,
                this.getProduct_name(),
                this.getPrice(),
                this.getCategory().getCat_name(),
                this.getCategory().getId());
    }



}
