package com.gamerssite.gamerssite.mappers.pc;

import com.gamerssite.gamerssite.dtos.pc.ProcessorDto;
import com.gamerssite.gamerssite.entity.Processor;
import com.gamerssite.gamerssite.mappers.IMapper;

import java.util.List;

public class ProcessorMapper implements IMapper<ProcessorDto, Processor> {

    @Override
    public ProcessorDto toDto(Processor entity) {
        if (entity == null) return null;
        return ProcessorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public List<ProcessorDto> toDtoList(List<Processor> entityList) {
        if (entityList == null) return null;
        return entityList.stream().map(this::toDto).toList();
    }
}
