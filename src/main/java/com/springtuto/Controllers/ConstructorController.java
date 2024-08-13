package com.springtuto.Controllers;

import com.springtuto.Mappers.ConstructorMapper;
import com.springtuto.DTOs.ConstructorDTO;
import com.springtuto.Requests.ConstructorRequest;
import com.springtuto.Services.ConstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/f1project/v1/constructors")
public class ConstructorController {

    private final ConstructorService constructorService;
    private final ConstructorMapper constructorMapper;

    @Autowired
    public ConstructorController(ConstructorService constructorService, ConstructorMapper constructorMapper) {
        this.constructorService = constructorService;
        this.constructorMapper = constructorMapper;
    }

    @GetMapping("/{constructorId}")
    public Optional<ConstructorDTO> getConstructorById(@PathVariable Long constructorId){
        return constructorService.tryGetConstructorById(constructorId);
    }

    @PutMapping
    public void registerConstructor(@RequestBody ConstructorRequest constructor){
        constructorService.registerConstructor(constructorMapper.toConstructor(constructor));
    }

    @GetMapping
    public List<ConstructorDTO> getAllConstructors(){
        return constructorService.tryGetAllConstructors();
    }

    @PostMapping("/{constructorId}")
    public void updateConstructorById(@PathVariable Long constructorId,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "nationality",required = false) String nationality,
                                      @RequestParam(value = "teamPrincipal",required = false) String teamPrincipal,
                                      @RequestParam(value = "teamLineUp", required = false) List<String> teamLineUp
                                      ){
        constructorService.tryUpdateConstructorById(constructorId, name, nationality, teamPrincipal, teamLineUp);
    }

    @DeleteMapping("/{constructorId}")
    public void deleteConstructorById(@PathVariable Long constructorId){
        constructorService.tryDeleteConstuctorById(constructorId);
    }
}
