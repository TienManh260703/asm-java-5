package com.example.shopapp.service.iplm;

import com.example.shopapp.model.Color;
import com.example.shopapp.repository.ColorRepository;
import com.example.shopapp.service.IColorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorServiceIplm implements IColorService {
    ColorRepository colorRepository;

    @Override
    public Page<Color> getColorPage(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }

    @Override
    public Color getColor(String id) {
        return colorRepository.findById(id).get();
    }

    @Override
    public void create(Color color) {
        if(colorRepository.existsByName(color.getName())) {
            return;
        }
        colorRepository.save(color);
    }

    @Override
    public void update(String id, Color color) {
        Optional<Color> colorOptional = colorRepository.findById(id);
        if (colorOptional.isPresent()) {
            if (colorRepository.existsByNameAndIdNot(color.getName(), id)) {
                return;
            }
            Color existingColor = colorOptional.get();
            existingColor.setName(color.getName());
            colorRepository.save(existingColor);
        }
    }

    @Override
    public void deleted(String id) {
        Optional<Color> colorOptional = colorRepository.findById(id);
        if (colorOptional.isPresent()) {
            Color existingColor = colorOptional.get();
            existingColor.setStatus(!existingColor.getStatus());
            colorRepository.save(existingColor);
        }
    }

    @Override
    public Page<Color> search(String name, Pageable pageable) {
        Page<Color> colors = colorRepository.findByNameContainingIgnoreCase(name, pageable);
        if (colors.isEmpty()) {
            colorRepository.findAll(pageable);
        }
        return colorRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
