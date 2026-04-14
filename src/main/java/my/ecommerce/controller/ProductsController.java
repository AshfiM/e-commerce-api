package my.ecommerce.controller;

import my.ecommerce.dto.ProductDto;
import my.ecommerce.models.ProductsEntity;
import my.ecommerce.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/prod")
public class ProductsController {
    private final ProductsService productsService;
    private static final String upload_dir = "uploads/";
    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }
    @GetMapping("/pros")
    public ResponseEntity<List<ProductDto>> allProducts(){
        return ResponseEntity.ok(productsService.getAllProducts().stream().map((prod)-> {
            ProductDto productDto = new ProductDto();
            productDto.setProd_id(prod.getProduct_id());
            productDto.setProd_name(prod.getProduct_name());
            productDto.setProd_price(prod.getPrice());
            productDto.setProd_cat(prod.getCategory().getCat_name());
            productDto.setCat_id(prod.getCategory().getCat_id());
            return productDto;
        }).toList());
    }

    @GetMapping("/lim/{max}")
    public ResponseEntity<List<ProductDto>> getLimitedProducts(@PathVariable int max) {
        return ResponseEntity.ok(productsService.getLimitedProducts(max).stream().map((productsEntity -> {
            ProductDto productDto = new ProductDto();
            productDto.setProd_id(productsEntity.getProduct_id());
            productDto.setProd_name(productsEntity.getProduct_name());
            productDto.setProd_price(productsEntity.getPrice());
            productDto.setProd_cat(productsEntity.getCategory().getCat_name());
            productDto.setCat_id(productsEntity.getCategory().getCat_id());
            return productDto;
        })).toList());
    }
    @PostMapping(value = "/insert",
    consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductDto> insertProd(@RequestBody ProductDto productDto) {
        ProductsEntity product = productsService.insertProduct(productDto.getCat_id(),
                productDto.getProd_name(), productDto.getProd_price());
        ProductDto newPro = new ProductDto();
        newPro.setProd_id(product.getProduct_id());
        newPro.setProd_name(product.getProduct_name());
        newPro.setProd_price(product.getPrice());
        newPro.setProd_cat(product.getCategory().getCat_name());
        newPro.setCat_id(product.getCategory().getCat_id());
        return ResponseEntity.ok(newPro);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            File directory = new File(upload_dir);
            if (!directory.exists()) {
                directory.mkdir();
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(upload_dir + file.getOriginalFilename());
            Files.write(path, bytes);
            return ResponseEntity.ok("file uploaded" + file.getOriginalFilename());
        } catch (IOException e) {
            System.out.println("file upload error");
            return ResponseEntity.internalServerError().build();
        }
    }
}
