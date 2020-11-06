package com.ggomjae.restapiserver.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonFilter("UserInfo")  // Filter 이용하는 어노테이션
public class User {
    private Integer id;

    @Size(min = 2)
    private String name;
}
