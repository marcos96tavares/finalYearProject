package com.example.Admin.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankHolidayResponse {

    private String division;
    private List<BankHolidayEvent> events;


}
