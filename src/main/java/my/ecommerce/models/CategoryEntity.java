package my.ecommerce.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long id;
    @Column
    private String cat_name;

    @OneToMany(mappedBy = "category")
    List<ProductEntity> products;

    public Long getId() {
        return id;
    }

    public CategoryEntity(){}
    public CategoryEntity(String catName){
        cat_name = catName;
    }

    public void setId(Long cat_id) {
        this.id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
