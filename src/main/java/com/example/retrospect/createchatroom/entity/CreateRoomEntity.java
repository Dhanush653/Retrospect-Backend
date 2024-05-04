package com.example.retrospect.createchatroom.entity;


import com.example.retrospect.topic.entity.TopicEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name="RoomDetails")
public class CreateRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomId;
    private String roomName;
    private String roomDescription;
    private String roomType;
    private String roomStatus = "active";
    private String room_image;
    private String room_startdate;
    private String room_enddate;



}
