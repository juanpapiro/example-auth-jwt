package br.com.fiap.productmanager.controller;

import br.com.fiap.productmanager.dto.ProductDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/product")
public class ProductController {

    @PreAuthorize("hasAnyRole('MERCHANT')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductDto productDto, Authentication auth) {
        log.info(productDto);
        return ResponseEntity.ok().build();
    }

}
