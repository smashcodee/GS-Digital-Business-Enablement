package br.com.smashcode.babycare.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceConnectionResponse {
    private boolean success;
    private Integer status;
    private String message;
}
