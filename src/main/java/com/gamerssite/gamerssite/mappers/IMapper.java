package com.gamerssite.gamerssite.mappers;

import java.util.List;

public interface IMapper<D, E> {
    D toDto(E entity);
    List<D> toDtoList(List<E> entityList);
}
