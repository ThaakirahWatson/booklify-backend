package com.booklify.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RegularUser")
public class RegularUser extends User{


}
