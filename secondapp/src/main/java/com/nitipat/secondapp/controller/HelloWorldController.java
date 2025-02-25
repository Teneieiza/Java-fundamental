package com.nitipat.secondapp.controller;

import com.nitipat.secondapp.dto.PostPersonalRecord;
import com.nitipat.secondapp.dto.PostTenRecord;
import com.nitipat.secondapp.service.HelloWorldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "helloworld")
@RequiredArgsConstructor
@Tag(name = "Personal", description = "Profile eieiza")
public class HelloWorldController {

    private final HelloWorldService helloWorldService;

    @GetMapping(value = "")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "HelloWorld", description = "send Hellooooooooworld")
    public String getHelloWorld(){
        return helloWorldService.getHelloWorld();
    }

    @PostMapping(value = "/post")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Personal", description = "Set profile by firstname and lastname")
    public String postPersonal(@Valid @RequestBody PostPersonalRecord request){
        return helloWorldService.postPersonal(request);
    }

    @GetMapping(value = "/getnumber/{number}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Amount number", description = "Calculate number in param equal (1 + number)*number")
    public  String getNumber(@PathVariable("number") Integer number ){
        return helloWorldService.getNumber(number);
    }

    @PostMapping(value = "/ten")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Info of Personal", description = "Add more info in profile")
    public PostTenRecord postObjectTen(@RequestParam("name") String name, @RequestParam("age") Integer age, @RequestParam("pet") String pet){
        PostTenRecord postTen = new PostTenRecord(name, age, pet);
        return helloWorldService.postObjectTen(postTen);
    }
}
