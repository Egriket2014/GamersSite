package com.gamerssite.gamerssite.mappers.pc;

import com.gamerssite.gamerssite.dtos.pc.GraphicCardDto;
import com.gamerssite.gamerssite.dtos.pc.MonitorDto;
import com.gamerssite.gamerssite.entity.Monitor;
import com.gamerssite.gamerssite.mappers.IMapper;

import java.util.List;

public class MonitorMapper implements IMapper<MonitorDto, Monitor> {
    @Override
    public MonitorDto toDto(Monitor entity) {
        if (entity == null) return null;
        return MonitorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public List<MonitorDto> toDtoList(List<Monitor> entityList) {
        if (entityList == null) return null;
        return entityList.stream().map(this::toDto).toList();
    }
}
