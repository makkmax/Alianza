package com.example.Alianza.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private String sharedKey;
    private String name;
    private String phone;
    private String email;
    private String startDate;
    private String endDate;




    private void generateSharedKey() {
        if (name != null && !name.isEmpty()) {
            String[] parts = name.split(" ");
            if (parts.length >= 2) {
                this.sharedKey = parts[0].substring(0, 1).toUpperCase() + parts[1].toUpperCase();
            }
        }
    }


}