package com.gamerssite.gamerssite.mappers.user;

import com.gamerssite.gamerssite.dtos.user.UserDto;
import com.gamerssite.gamerssite.entity.User;
import com.gamerssite.gamerssite.mappers.IMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements IMapper<UserDto, User> {

    @Override
    public UserDto toDto(User entity) {
        if (entity == null) return null;
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .picture(entity.getPicture())
                .graphicCard(entity.getGraphicCard() == null ? null : entity.getGraphicCard().toString())
                .memory(entity.getMemory() == null ? null : entity.getMemory().toString())
                .monitor(entity.getMonitor() == null ? null : entity.getMonitor().toString())
                .processor(entity.getProcessor() == null ? null : entity.getProcessor().toString())
                .build();
    }

    @Override
    public List<UserDto> toDtoList(List<User> entityList) {
        if (entityList == null) return null;
        return entityList.stream().map(this::toDto).toList();
    }
}
