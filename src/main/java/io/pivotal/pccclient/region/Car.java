package io.pivotal.pccclient.region;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import javax.persistence.Table;
import java.io.Serializable;

@Region("formula1region")
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Car implements Serializable {

	@Id
	private String brand;
	private String country;
    private String engine;
	private int wins;

}