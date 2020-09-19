package com.herya.controller;

import com.herya.exception.ResourceNotFoundException;
import com.herya.model.DataNama;
import com.herya.repository.DataNamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DataNamaController {
    @Autowired
    DataNamaRepository dataNamaRepository;

    @GetMapping("/datanama")
    public ResponseEntity<List<DataNama>> getAllDataNama(@RequestParam(required = false)String datanama){
    try{
        List<DataNama> dataNamas = new ArrayList<DataNama>();

        if (datanama == null)
            dataNamaRepository.findAll().forEach(dataNamas::add);
        else
            dataNamaRepository.findByNamaContaining(datanama).forEach(dataNamas::add);
        if (dataNamas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dataNamas, HttpStatus.OK);
    } catch (Exception e){
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    @GetMapping("/siswa/{id}")
    public ResponseEntity<DataNama> getDataNamaById(@PathVariable("id") Long id){
        Optional<DataNama> dataNama = dataNamaRepository.findById(id);

        if (dataNama.isPresent()){
            return new ResponseEntity<>(dataNama.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/datanama")
    public ResponseEntity<DataNama>createDataNama(@RequestBody DataNama dataNama){
        try{
            DataNama _datanama = dataNamaRepository
                    .save(new DataNama(dataNama.getNama(), dataNama.getKelas(), dataNama.getNo_absen()));
            return new ResponseEntity<>(_datanama, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PutMapping("/datanama/{id}")
    public ResponseEntity<DataNama> updateDataNama(@PathVariable("id") long id, @RequestBody DataNama dataNama){
        Optional<DataNama> datanama = dataNamaRepository.findById(id);
        if (datanama.isPresent()){
            DataNama _datanama = datanama.get();
            _datanama.setNama(dataNama.getNama());
            _datanama.setKelas(dataNama.getKelas());
            _datanama.setNo_absen(dataNama.getNo_absen());
            return new ResponseEntity<>(dataNamaRepository.save(_datanama), HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/datanama/{id}")
    public ResponseEntity<HttpStatus> deleteDataNama(@PathVariable("id") long id){
        try{
            dataNamaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    @DeleteMapping("/datanama")
    public ResponseEntity<HttpStatus> deleteAllDataNama(){
        try{
            dataNamaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
