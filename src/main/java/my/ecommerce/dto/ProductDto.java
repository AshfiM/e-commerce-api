package my.ecommerce.dto;

public class ProductDto {
    private Long prod_id;
    private String prod_name;
    private float prod_price;
    private String prod_cat;
    private Long cat_id;

    public Long getCat_id() {
        return cat_id;
    }

    public ProductDto() {
    }

    public ProductDto(Long prod_id, String prod_name, float prod_price, String prod_cat, Long cat_id) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.prod_cat = prod_cat;
        this.cat_id = cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public Long getProd_id() {
        return prod_id;
    }

    public void setProd_id(Long prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public float getProd_price() {
        return prod_price;
    }

    public void setProd_price(float prod_price) {
        this.prod_price = prod_price;
    }

    public String getProd_cat() {
        return prod_cat;
    }

    public void setProd_cat(String prod_cat) {
        this.prod_cat = prod_cat;
    }
}
