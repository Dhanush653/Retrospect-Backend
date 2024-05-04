package com.example.retrospect.createchatroom.service;

import com.example.retrospect.createchatroom.entity.CreateRoomEntity;

import java.util.List;

public interface IRoomService {
    List<CreateRoomEntity> getAllRooms();

    CreateRoomEntity createRoom(CreateRoomEntity createRoomEntity);

    CreateRoomEntity updateRoom(long  roomId, CreateRoomEntity updatedRoomEntity);
}
