package com.nitipat.secondapp.controller;

import com.nitipat.secondapp.dto.GetPersonalByIDRecord;
import com.nitipat.secondapp.dto.PersonalRecord;
import com.nitipat.secondapp.dto.PutPersonalRecord;
import com.nitipat.secondapp.entity.Personal;
import com.nitipat.secondapp.service.PersonalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "personal")
@RequiredArgsConstructor
@Tag(name = "Personal", description = "Personal ngai lar")
public class PersonalController {

    private final PersonalService personalService;

    @Operation(summary = "Get All PersonalData")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "")
    public List<Personal> getAllPersonal(){
        return personalService.getAllPersonal();
    }

    @Operation(summary = "Get PersonalData By ID")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Personal getPersonalByID(@PathVariable("id") UUID request){
        return personalService.getPersonalByID(request);
    }

    @Operation(summary = "Create Personal")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create")
    public Personal createPersonal(@Valid @RequestBody PersonalRecord request){
        return personalService.createPersonal(request);
    }

    @Operation(summary = "Edit Personal By ID")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/edit/{id}")
    public Personal editPersonal(@PathVariable("id") String id,@Valid @RequestBody PutPersonalRecord request){
        return personalService.editPersonal(id, request);
    }

    @Operation(summary = "Delete Personal")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/delete")
    public String deletePersonal(@Valid @RequestBody String id){
        var IDTrim = id.trim();
        return personalService.deletePersonal(IDTrim);
    }

}
