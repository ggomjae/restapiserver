package com.ggomjae.restapiserver.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer id;

    @Size(min = 2)
    private String name;
}
