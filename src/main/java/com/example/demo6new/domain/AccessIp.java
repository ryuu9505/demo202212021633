package com.example.demo6new.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ACCESS_IP")
@Data
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessIp {

    @Id
    @GeneratedValue
    @Column(name = "ip_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "ip_address", nullable = false)
    private String address;

}
