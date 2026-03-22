package my.ecommerce.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cat_id;
    @Column
    private String cat_name;

    @OneToMany(mappedBy = "category")
    List<ProductsEntity> products;

    public Long getCat_id() {
        return cat_id;
    }

    public CategoryEntity(){}
    public CategoryEntity(String catName){
        cat_name = catName;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public List<ProductsEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsEntity> products) {
        this.products = products;
    }
}
