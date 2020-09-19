package com.herya.repository;

import com.herya.model.DataNama;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataNamaRepository extends JpaRepository<DataNama, Long> {
    List<DataNama> findByKelas(String kelas);
    List<DataNama> findByNamaContaining(String nama);
}
