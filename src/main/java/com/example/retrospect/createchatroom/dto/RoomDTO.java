package com.example.retrospect.createchatroom.dto;

import lombok.Data;

@Data
public class RoomDTO {
    private int roomId;
    private String roomName;
    private String roomDescription;
    private String roomType;
    private String roomStatus;
    private String roomCreatedBy;
}
