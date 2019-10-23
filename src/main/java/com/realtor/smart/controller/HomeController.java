package com.realtor.smart.controller;

import com.realtor.smart.domain.SmartHouse;
import com.realtor.smart.domain.User;
import com.realtor.smart.dto.UserHouseDTO;
import com.realtor.smart.service.SmartHouseService;
import com.realtor.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.realtor.smart.util.Constants.AUTHORIZATION;

//Создание дома, для назначения домов юзерам, проверка юзера

@RestController
@RequestMapping("home")
public class HomeController {

    private final UserService service;

    private final SmartHouseService houseService;

    @Autowired
    public HomeController(UserService service, SmartHouseService houseService) {
        this.service = service;
        this.houseService = houseService;
    }

    @PostMapping("/create")
    public void save(@RequestBody SmartHouse house) {
        houseService.create(house);
    }


    @PostMapping(value = "/assign")
    public ResponseEntity<String> assign(@RequestBody UserHouseDTO userHouseDTO, HttpServletRequest request) {

        ResponseEntity<String> entity = new ResponseEntity<>("Unknown user or incorrect names of houses", HttpStatus.FORBIDDEN);

        User user = userHouseDTO.getUser();
        List<SmartHouse> houses = userHouseDTO.getHouses();
        if (checkUserToken(user, request) && houseService.checkExistingHouses(houses)) {

            List<String> names = houses.stream().map(SmartHouse::getName).collect(Collectors.toList());

            houseService.assignHousesToUser(user, names);
            entity = ResponseEntity.ok("Houses are assign to " + user.getName() + " user");
        }

        return entity;
    }

    @GetMapping("/list/houses/user/{name}/{password}")
    public ResponseEntity<List<SmartHouse>> getHousesForUser(@PathVariable("name") String name,
                                                             @PathVariable("password") String password,
                                                             HttpServletRequest request) {
        User user = new User(name, password);

        ResponseEntity<List<SmartHouse>> entity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.FORBIDDEN);

        if (checkUserToken(user, request)) {
            entity = ResponseEntity.ok(houseService.getHousesForUser(user));
        }

        return entity;
    }

    private boolean checkUserToken(User user, HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);

        return service.existsUserAndToken(user, token);

    }

}
