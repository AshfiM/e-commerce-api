package my.ecommerce.controller;

import my.ecommerce.dto.ProductDto;
import my.ecommerce.models.ProductEntity;
import my.ecommerce.service.ProductsService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
            productDto.setProd_id(prod.getId());
            productDto.setProd_name(prod.getProduct_name());
            productDto.setProd_price(prod.getPrice());
            productDto.setProd_cat(prod.getCategory().getCat_name());
            productDto.setCat_id(prod.getCategory().getId());
            return productDto;
        }).toList());
    }

    @GetMapping("/lim/{max}")
    public ResponseEntity<List<ProductDto>> getLimitedProducts(@PathVariable int max) {
        return ResponseEntity.ok(productsService.getLimitedProducts(max).stream().map((productsEntity -> {
            ProductDto productDto = new ProductDto();
            productDto.setProd_id(productsEntity.getId());
            productDto.setProd_name(productsEntity.getProduct_name());
            productDto.setProd_price(productsEntity.getPrice());
            productDto.setProd_cat(productsEntity.getCategory().getCat_name());
            productDto.setCat_id(productsEntity.getCategory().getId());
            return productDto;
        })).toList());
    }
    @PostMapping(value = "/insert",
    consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductDto> insertProd(@RequestBody ProductDto productDto) {
        ProductEntity product = productsService.insertProduct(productDto.getCat_id(),
                productDto.getProd_name(), productDto.getProd_price());
        ProductDto newPro = new ProductDto();
        newPro.setProd_id(product.getId());
        newPro.setProd_name(product.getProduct_name());
        newPro.setProd_price(product.getPrice());
        newPro.setProd_cat(product.getCategory().getCat_name());
        newPro.setCat_id(product.getCategory().getId());
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
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) {
        try {
            Path path = Paths.get(upload_dir).resolve(fileName).normalize();
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; fileName=\"" + resource.getFilename()+"\"")
                        .body(resource);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("download error"+e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
