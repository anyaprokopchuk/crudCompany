package org.prokopchuk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Company {
    @NonNull
    private final Integer id;
    private String name;
    private String headquarters;
    private int founding_year;
    private int number_of_employees;


}
