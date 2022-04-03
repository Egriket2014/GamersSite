package com.gamerssite.gamerssite.mappers.game;

import com.gamerssite.gamerssite.dtos.game.GameDto;
import com.gamerssite.gamerssite.entity.Game;
import com.gamerssite.gamerssite.mappers.IMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameMapper implements IMapper<GameDto, Game> {

    @Override
    public GameDto toDto(Game entity) {
        if (entity == null) return null;
        return GameDto.builder()
                .title(entity.getTitle())
                .rating(entity.getRating())
                .link(entity.getLink())
                .image(entity.getImage())
                .price(entity.getPrice())
                .minGraphicCard(entity.getMinGraphicCard())
                .minProcessor(entity.getMinProcessor())
                .minRam(entity.getMinRam())
                .diskMemory(entity.getDiskMemory())
                .build();
    }

    @Override
    public List<GameDto> toDtoList(List<Game> entityList) {
        if (entityList == null) return null;
        return entityList.stream().map(this::toDto).toList();
    }
}
