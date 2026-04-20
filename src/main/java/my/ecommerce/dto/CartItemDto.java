package my.ecommerce.dto;

public class CartItemDto {

    private int qty;
    private Long productId;

    public CartItemDto(int qty, Long productId) {
        this.qty = qty;
        this.productId = productId;
    }

    public CartItemDto() {
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
