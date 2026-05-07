package com.bankAccount.driven.record;

import java.util.Date;

/**
 * POJO for User data.
 */
public class UserDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private Date birthday;

    /**
     * Constructor with all parameters for the user.
     * @param id of the user.
     * @param name of the user.
     * @param surname of the user.
     * @param email of the user.
     * @param birthday of the user.
     */
    public UserDTO(String id, String name, String surname, String email, Date birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
    }

    /**
     * Constructor with only userId.
     * @param id of the user.
     */
    public UserDTO(String id){
        this(id, new String(), new String(), new String(), null);
    }

    /**
     * Get id of user.
     * @return id of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Get the name of the user
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Override of generic equal to catch only the id.
     * @param o user to compare.
     */
    @Override
    public boolean equals(Object o){
        return o instanceof UserDTO userDTO && userDTO.id.equals(this.id);
    }
}
