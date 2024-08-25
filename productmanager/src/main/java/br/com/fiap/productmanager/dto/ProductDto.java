package br.com.fiap.productmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDto {

    private String name;
    private String description;
    private BigDecimal price;

}
