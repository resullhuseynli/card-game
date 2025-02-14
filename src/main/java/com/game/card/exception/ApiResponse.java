package com.game.card.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String id;

    private Date errorTime;

    private T error;

}

