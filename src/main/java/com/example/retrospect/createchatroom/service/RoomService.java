package com.example.retrospect.createchatroom.service;

import com.example.retrospect.createchatroom.entity.CreateRoomEntity;
import com.example.retrospect.createchatroom.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service

public class RoomService implements IRoomService{

    @Autowired
    private IRoomRepository roomRepository;


    @Override
    public List<CreateRoomEntity> getAllRooms() {

        return roomRepository.findAll();
    }

    @Override
    public CreateRoomEntity createRoom(CreateRoomEntity createRoomEntity) {
        return roomRepository.save(createRoomEntity);
    }

    @Override

    public CreateRoomEntity updateRoom(long roomId, CreateRoomEntity updatedRoomEntity) {

        Optional<CreateRoomEntity> optionalRoomEntity = roomRepository.findById(roomId);
        if (optionalRoomEntity.isPresent()) {
            CreateRoomEntity roomEntity = optionalRoomEntity.get();


            roomEntity.setRoomName(updatedRoomEntity.getRoomName());
            roomEntity.setRoomDescription(updatedRoomEntity.getRoomDescription());
            roomEntity.setRoom_startdate(updatedRoomEntity.getRoom_startdate());
            roomEntity.setRoomStatus(updatedRoomEntity.getRoomStatus());
            roomEntity.setRoom_enddate(updatedRoomEntity.getRoom_enddate());
            roomEntity.setRoom_image(updatedRoomEntity.getRoom_image());

            return roomRepository.save(roomEntity);
        } else {
            throw new NoSuchElementException("Room with id " + roomId + " not found");
        }
    }

}
