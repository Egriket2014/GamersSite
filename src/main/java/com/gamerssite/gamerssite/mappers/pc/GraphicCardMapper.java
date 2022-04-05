package com.gamerssite.gamerssite.mappers.pc;

import com.gamerssite.gamerssite.dtos.pc.GraphicCardDto;
import com.gamerssite.gamerssite.entity.GraphicCard;
import com.gamerssite.gamerssite.mappers.IMapper;

import java.util.List;

public class GraphicCardMapper implements IMapper<GraphicCardDto, GraphicCard> {

    @Override
    public GraphicCardDto toDto(GraphicCard entity) {
        if (entity == null) return null;
        return GraphicCardDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public List<GraphicCardDto> toDtoList(List<GraphicCard> entityList) {
        if (entityList == null) return null;
        return entityList.stream().map(this::toDto).toList();
    }
}
