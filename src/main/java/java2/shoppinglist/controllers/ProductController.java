package java2.shoppinglist.controllers;

import java2.shoppinglist.domains.Product;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.dtos.ProductDTO;
import java2.shoppinglist.services.products.add.AddProductRequest;
import java2.shoppinglist.services.products.add.AddProductResponse;
import java2.shoppinglist.services.products.add.AddProductService;
import java2.shoppinglist.services.products.getAll.GetAllProductsRequest;
import java2.shoppinglist.services.products.getAll.GetAllProductsResponse;
import java2.shoppinglist.services.products.getAll.GetAllProductsService;
import java2.shoppinglist.services.products.remove.RemoveProductRequest;
import java2.shoppinglist.services.products.remove.RemoveProductResponse;
import java2.shoppinglist.services.products.remove.RemoveProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/product/")
public class ProductController {

    @Autowired
    private AddProductService addProductService;

    @Autowired
    private RemoveProductService removeProductService;

    @Autowired
    private GetAllProductsService getAllProductsService;


    @PostMapping(value = "/add")
    public ResponseEntity addProduct(@RequestBody ProductDTO productDTO) {

        AddProductRequest request = new AddProductRequest(
                productDTO.getTitle(), productDTO.getDescription(), productDTO.getQuantity(), productDTO.getShoppingList());
        AddProductResponse response = addProductService.execute(request);

        if (!response.getShoppingListErrors().isEmpty()) {
            return new ResponseEntity<>(response.getShoppingListErrors(), HttpStatus.BAD_REQUEST);
        }
        productDTO.setId(response.getProduct().getId());
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/remove")
    public ResponseEntity removeProduct(@RequestBody ProductDTO productDTO) {

        RemoveProductRequest request = new RemoveProductRequest(productDTO.getShoppingList(), productDTO.getTitle());
        RemoveProductResponse response = removeProductService.execute(request);

        if (!response.getShoppingListErrors().isEmpty()) {
            return new ResponseEntity<>(response.getShoppingListErrors(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/getall")
    public ResponseEntity getAllProducts(@RequestBody ShoppingList shoppingList) {

        GetAllProductsRequest request = new GetAllProductsRequest(shoppingList);
        GetAllProductsResponse response = getAllProductsService.execute(request);

        if (!response.getShoppingListErrors().isEmpty()) {
            return new ResponseEntity<>(response.getShoppingListErrors(), HttpStatus.NOT_FOUND);
        }
        List<Product> products = response.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
