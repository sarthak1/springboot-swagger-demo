package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Address {

    @Id
    @Column(name = "address_id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "street")
    private String street;
    @NonNull
    @Column(name = "city")
    private String city;
    @NonNull
    @Column(name = "state")
    private String state;
    @NonNull
    @Column(name = "zip")
    private String zip;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Customer> customers = new ArrayList<>();


}
