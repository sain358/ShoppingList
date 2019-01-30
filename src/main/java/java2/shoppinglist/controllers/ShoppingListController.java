package java2.shoppinglist.controllers;

import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.domains.User;
import java2.shoppinglist.dtos.ShoppingListDTO;
import java2.shoppinglist.services.shoppinglists.add.AddShoppingListRequest;
import java2.shoppinglist.services.shoppinglists.add.AddShoppingListResponse;
import java2.shoppinglist.services.shoppinglists.add.AddShoppingListService;
import java2.shoppinglist.services.shoppinglists.get.GetShoppingListRequest;
import java2.shoppinglist.services.shoppinglists.get.GetShoppingListResponse;
import java2.shoppinglist.services.shoppinglists.get.GetShoppingListService;
import java2.shoppinglist.services.shoppinglists.getAll.GetAllShoppingListsRequest;
import java2.shoppinglist.services.shoppinglists.getAll.GetAllShoppingListsResponse;
import java2.shoppinglist.services.shoppinglists.getAll.GetAllShoppingListsService;
import java2.shoppinglist.services.shoppinglists.remove.RemoveShoppingListRequest;
import java2.shoppinglist.services.shoppinglists.remove.RemoveShoppingListResponse;
import java2.shoppinglist.services.shoppinglists.remove.RemoveShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/shoppingLists")
public class ShoppingListController {

    @Autowired
    private AddShoppingListService addShoppingListService;

    @Autowired
    private RemoveShoppingListService removeShoppingListService;

    @Autowired
    private GetShoppingListService getShoppingListService;

    @Autowired
    private GetAllShoppingListsService getAllShoppingListsService;


    @PostMapping
    public ResponseEntity addShoppingList(@RequestBody ShoppingListDTO shoppingListDTO) {

        AddShoppingListRequest request = new AddShoppingListRequest(
                shoppingListDTO.getUser(), shoppingListDTO.getTitle());
        AddShoppingListResponse response = addShoppingListService.execute(request);

        if (!response.getShoppingListErrors().isEmpty()) {
            return new ResponseEntity<>(response.getShoppingListErrors(), HttpStatus.BAD_REQUEST);
        }
        shoppingListDTO.setId(response.getShoppingList().getId());
        return new ResponseEntity<>(shoppingListDTO, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity removeShoppingList(@RequestBody ShoppingListDTO shoppingListDTO) {

        RemoveShoppingListRequest request = new RemoveShoppingListRequest(
                shoppingListDTO.getUser(), shoppingListDTO.getTitle());
        RemoveShoppingListResponse response = removeShoppingListService.execute(request);

        if (!response.getShoppingListErrors().isEmpty()) {
            return new ResponseEntity<>(response.getShoppingListErrors(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/get")
    public ResponseEntity getShoppingList(@RequestBody ShoppingListDTO shoppingListDTO) {

        GetShoppingListRequest request = new GetShoppingListRequest(
                shoppingListDTO.getUser(), shoppingListDTO.getTitle());
        GetShoppingListResponse response = getShoppingListService.execute(request);

        if (!response.getShoppingListErrors().isEmpty()) {
            return new ResponseEntity<>(response.getShoppingListErrors(), HttpStatus.NOT_FOUND);
        }
        shoppingListDTO.setId(response.getShoppingList().getId());
        return new ResponseEntity<>(shoppingListDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/getall")
    public ResponseEntity getAllShoppingLists(@RequestBody User user) {

        GetAllShoppingListsRequest request = new GetAllShoppingListsRequest(user);
        GetAllShoppingListsResponse response = getAllShoppingListsService.execute(request);

        if (!response.getShoppingListErrors().isEmpty()) {
            return new ResponseEntity<>(response.getShoppingListErrors(), HttpStatus.NOT_FOUND);
        }
        List<ShoppingList> shoppingLists = response.getShoppingLists();
        return new ResponseEntity<>(shoppingLists, HttpStatus.OK);
    }

}
