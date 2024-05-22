package com.example.shopapp.service.iplm;

import com.example.shopapp.model.Size;
import com.example.shopapp.repository.SizeRepository;
import com.example.shopapp.service.ISizeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SizeServiceIplm implements ISizeService {
    SizeRepository sizeRepository;

    @Override
    public Page<Size> getSizes(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public Page<Size> search(Float name, Pageable pageable) {
        Page<Size> sizes = sizeRepository.findSizeByName(name, pageable);
        if (sizes.isEmpty()) sizes = sizeRepository.findAll(pageable);
        return sizes;
    }

    @Override
    public Size getSize(String id) {
        Optional<Size> sizeOptional = sizeRepository.findById(id);
        if (sizeOptional.isPresent()) {
            return sizeOptional.get();
        }
        return new Size();
    }

    @Override
    public void create(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void update(String id, Size size) {
        Optional<Size> sizeOptional = sizeRepository.findById(id);
        if (sizeOptional.isPresent()) {
            Size existingSize = sizeOptional.get();
            existingSize.setName(size.getName());
            sizeRepository.save(existingSize);
        }
    }

    @Override
    public void deleted(String id) {
        Optional<Size> sizeOptional = sizeRepository.findById(id);
        if (sizeOptional.isPresent()) {
            Size existingSize = sizeOptional.get();
            existingSize.setStatus(!existingSize.getStatus());
            sizeRepository.save(existingSize);
        }
    }

    @Override
    public boolean existsByNameAndIdNot(Float name, String id) {
        return sizeRepository.existsByNameAndIdNot(name,id);
    }

    @Override
    public boolean existsByName(Float name) {
        return sizeRepository.existsByName(name);
    }

    @Override
    public List<Size> findByStatusFalse() {
        return sizeRepository.findByStatusFalse();
    }
}
