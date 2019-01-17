package java2.shoppinglist.controllers;

import java2.shoppinglist.dtos.UserDTO;
import java2.shoppinglist.services.users.get.GetUserRequest;
import java2.shoppinglist.services.users.get.GetUserResponse;
import java2.shoppinglist.services.users.get.GetUserService;
import java2.shoppinglist.services.users.registration.UserRegistrationRequest;
import java2.shoppinglist.services.users.registration.UserRegistrationResponse;
import java2.shoppinglist.services.users.registration.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private GetUserService getUserService;

    @PostMapping("/user/register")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {

        UserRegistrationRequest request = new UserRegistrationRequest(
                userDTO.getLogin(), userDTO.getPassword(), userDTO.getPassword());
        UserRegistrationResponse response = userRegistrationService.execute(request);

        if (!response.getShoppingListErrors().isEmpty()) {
            return new ResponseEntity<>(response.getShoppingListErrors(), HttpStatus.BAD_REQUEST);
        }
        userDTO.setId(response.getUser().getId());
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    //@ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.POST/*, consumes = "application/json", produces = "application/json"*/)
    public ResponseEntity getUser(@RequestBody UserDTO userDTO) {

        GetUserRequest request = new GetUserRequest(userDTO.getLogin(), userDTO.getPassword());
        GetUserResponse response = getUserService.execute(request);

        if (!response.getShoppingListErrors().isEmpty()) {
            return new ResponseEntity<>(response.getShoppingListErrors(), HttpStatus.NOT_FOUND);
        }
        userDTO.setId(response.getUser().getId());
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping(value = "/view")
    public String printTest(ModelMap model) {
        model.addAttribute("message", "You see '.jpa' page!");
        return "noXml";
    }
}
