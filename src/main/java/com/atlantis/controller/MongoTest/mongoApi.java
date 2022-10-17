package com.atlantis.controller.MongoTest;

import com.atlantis.model.MongoTest.TestModel;
import com.atlantis.repository.MongoTest.MongoTestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongoTest")
public class mongoApi {

    @Autowired
    private MongoTestRepo repo;

    @PostMapping
    public ResponseEntity<TestModel> add(@RequestBody TestModel tm){
        return ResponseEntity.ok(repo.save(tm));
    }
    @GetMapping
    public ResponseEntity<List<TestModel>> getAll(){
        return ResponseEntity.ok(repo.findAll());
    }
}
