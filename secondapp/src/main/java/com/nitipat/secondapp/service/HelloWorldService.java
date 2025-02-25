package com.nitipat.secondapp.service;

import com.nitipat.secondapp.dto.PostPersonalRecord;
import com.nitipat.secondapp.dto.PostTenRecord;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public String getHelloWorld(){
        return "Helloooooooooooworld";
    }

    public  String postPersonal(PostPersonalRecord request){
        return "I got firstname: " + request.firstname() + " and Lastname: " + request.lastname();
    }

    public  String getNumber(Integer a){
        return String.format("I have ( 1 + %d ) * %d = %d", a, a, (1 + a) * a);
    }

    public PostTenRecord postObjectTen(PostTenRecord request){
        return new PostTenRecord(request.name(),request.age(),request.pet());
    }
}
