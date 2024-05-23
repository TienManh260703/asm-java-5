package com.example.shopapp.service.iplm;

import com.example.shopapp.service.IColorService;
import com.example.shopapp.service.IOrderDetailService;
import com.example.shopapp.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailServiceIplm implements IOrderDetailService {

}
