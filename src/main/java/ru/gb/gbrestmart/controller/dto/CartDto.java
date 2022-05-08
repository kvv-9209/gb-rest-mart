package ru.gb.hhh;

import lombok.*;
import ru.gb.gbrestmart.entity.enums.Status;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    private Long id;
    @NotNull
    private Status status;
}
