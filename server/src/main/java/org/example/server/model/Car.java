package org.example.server.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_car;

    private String brand;

    private String model;

    private String color;

    private String type;

    @Column(name = "number_car", nullable = false)
    private String numberCar;

    public Long getId() {
        return id_car;
    }

    public void setId(Long id_car) {
        this.id_car = id_car;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumberCar() {
        return numberCar;
    }

    public void setNumberCar(String numberCar) {
        this.numberCar = numberCar;
    }
}
