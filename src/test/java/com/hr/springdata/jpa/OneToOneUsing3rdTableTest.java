package com.hr.springdata.jpa;

import com.hr.springdata.jpa.onetoonebidirectional.repository.LaptopRepository;
import com.hr.springdata.jpa.onetooneunidirectional.entity.Product;
import com.hr.springdata.jpa.onetooneunidirectional.entity.UserManual;
import com.hr.springdata.jpa.onetooneunidirectional.repository.ProductRepository;
import com.hr.springdata.jpa.onetooneunidirectional.repository.UserManualRepository;
import com.hr.springdata.jpa.onetooneusing3rdtable.entity.House;
import com.hr.springdata.jpa.onetooneusing3rdtable.entity.ParkingSpace;
import com.hr.springdata.jpa.onetooneusing3rdtable.repository.HouseRepository;
import com.hr.springdata.jpa.onetooneusing3rdtable.repository.ParkingSpaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OneToOneUsing3rdTableTest {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Test
    void addHouseWithParking()
    {

        final ParkingSpace parkingSpace = ParkingSpace.builder()
                .parkingType("2-wheeler")
                .build();

        final House house = House.builder()
                .houseName("77-North, Bangalore")
                .parkingSpace(parkingSpace)
                .build();

        houseRepository.save(house);
    }

    @Test
    void addParkingWithHouse()
    {
        final House house = House.builder()
                .houseName("15-East, Bangalore")
                .build();

        final ParkingSpace parkingSpace = ParkingSpace.builder()
                .parkingType("4-wheeler")
                .house(house)
                .build();

        house.setParkingSpace(parkingSpace);
        parkingSpaceRepository.save(parkingSpace);
    }

    @Test
    void getHouses()
    {
        final List<House> houses = houseRepository.findAll();
        System.out.println("Results : "+houses);
    }

    @Test
    void getParkingSpaces()
    {
        final List<ParkingSpace> parkingSpaces = parkingSpaceRepository.findAll();
        System.out.println("Results : "+parkingSpaces);
    }
}
