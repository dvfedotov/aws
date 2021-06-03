package ru.dfed.aws.web.rest;


import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dfed.aws.domain.dto.ProductDTO;
import ru.dfed.aws.service.ProductService;


@RestController
@RequestMapping("/api")
public class ProductController {

    private static final String ENTITY_NAME = "catalogProduct";
    private final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PutMapping("/product/{id}/add")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to update Product : {}", productDTO);
        if (productDTO.getId() == null) {
            throw new RuntimeException();
        }
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.ok()
            .body(result);
    }


    @GetMapping("/product")
    public List<ProductDTO> getAllProducts() {
        log.debug("REST request to get all Products");
        return productService.findAll();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        return ResponseEntity.ok(productService.findOne(id));
    }

//    @DeleteMapping("/product/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        log.debug("REST request to delete Product : {}", id);
//        productService.delete(id);
//        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
//    }
}
