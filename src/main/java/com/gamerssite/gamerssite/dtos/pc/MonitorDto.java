package com.gamerssite.gamerssite.dtos.pc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonitorDto {

    private long id;
    private String name;
}