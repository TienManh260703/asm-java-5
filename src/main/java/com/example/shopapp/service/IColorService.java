package com.example.shopapp.service;

import com.example.shopapp.model.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IColorService {

    Page<Color> getColorPage(Pageable pageable);
    Color getColor (String id);
    void create(Color color);
    void update(String id ,Color color);
    void  deleted(String id);
    Page<Color> search (String name , Pageable pageable);
}
