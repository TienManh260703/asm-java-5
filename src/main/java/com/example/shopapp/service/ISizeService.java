package com.example.shopapp.service;

import com.example.shopapp.model.Color;
import com.example.shopapp.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISizeService {
    Page<Size> getSizes(Pageable pageable);

    Page<Size> search(Float name, Pageable pageable);

    Size getSize(String id);

    void create(Size size);

    void update(String id, Size size);

    void deleted(String id);
    boolean existsByNameAndIdNot(Float name, String id);
    boolean existsByName(Float name);
    List<Size> findByStatusFalse();

}
