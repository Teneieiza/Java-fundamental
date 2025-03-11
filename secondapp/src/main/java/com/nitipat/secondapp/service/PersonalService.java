package com.nitipat.secondapp.service;

import com.nitipat.secondapp.constants.ErrorMessage;
import com.nitipat.secondapp.dto.PersonalRecord;
import com.nitipat.secondapp.dto.PutPersonalRecord;
import com.nitipat.secondapp.entity.Personal;
import com.nitipat.secondapp.exception.InvalidException;
import com.nitipat.secondapp.exception.NotFoundException;
import com.nitipat.secondapp.repository.PesonalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalService {
    private  final PesonalRepository pesonalRepository;

    public List<Personal> getAllPersonal(){
        return pesonalRepository.findAll();
    }

    public Personal getPersonalByID(UUID id){
        return pesonalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND.formatted("Personal")));
    }

    public Personal createPersonal(PersonalRecord request) {
        try {
            var personal = new Personal();
            personal.setFirstname(request.firstname());
            personal.setLastname(request.lastname());
            personal.setNickname(request.nickname());
            personal.setAge(request.age());

            pesonalRepository.save(personal);
            return personal;

        } catch (InvalidException e) {
            log.error("Invalid request: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Fail to create PersonalData: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Personal editPersonal(String id, PutPersonalRecord request){
        try {
            var updatePersonal = getPersonalByID(UUID.fromString(id));
            updatePersonal.setFirstname(Optional.ofNullable(request.firstname()).filter(s -> !s.isBlank()).orElse(updatePersonal.getFirstname()));
            updatePersonal.setLastname(Optional.ofNullable(request.lastname()).filter(s -> !s.isBlank()).orElse(updatePersonal.getLastname()));
            updatePersonal.setNickname(Optional.ofNullable(request.nickname()).filter(s -> !s.isBlank()).orElse(updatePersonal.getNickname()));
            updatePersonal.setAge(Optional.ofNullable(request.age()).orElse(updatePersonal.getAge()));
            return updatePersonal;

        }catch (InvalidException e){
            log.error("Invalid request: {}", e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error("Fail to create PersonalData: {}", e.getMessage(), e);
            throw e;
        }
    }

    public String deletePersonal(String id){
        log.info(id);
        var deletePersonal = getPersonalByID(UUID.fromString(id));
        pesonalRepository.delete(deletePersonal);
        return String.format("ID: %s has been deleted!!", id);
    }
}
