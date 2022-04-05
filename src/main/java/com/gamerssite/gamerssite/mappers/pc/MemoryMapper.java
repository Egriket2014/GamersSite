package com.gamerssite.gamerssite.mappers.pc;

import com.gamerssite.gamerssite.dtos.pc.MemoryDto;
import com.gamerssite.gamerssite.entity.Memory;
import com.gamerssite.gamerssite.mappers.IMapper;

import java.util.List;

public class MemoryMapper implements IMapper<MemoryDto, Memory> {

    @Override
    public MemoryDto toDto(Memory entity) {
        if (entity == null) return null;
        return MemoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public List<MemoryDto> toDtoList(List<Memory> entityList) {
        if (entityList == null) return null;
        return entityList.stream().map(this::toDto).toList();
    }
}
