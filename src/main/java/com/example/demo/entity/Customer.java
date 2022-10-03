package com.example.demo.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer {

    @Id
    @Column(name = "cust_id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NonNull
    @Column(name = "first_name")
    private String firstName;


    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    //@ToString.Exclude
    private Address address;


}
