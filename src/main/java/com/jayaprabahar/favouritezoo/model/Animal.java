package com.jayaprabahar.favouritezoo.model;

import com.jayaprabahar.favouritezoo.dto.AnimalResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

/**
 * <p> Project : favouritezoo </p>
 * <p> Title : Animal.java </p>
 * <p> Description: Animal Entity </p>
 * <p> Created: Nov 10, 2020 </p>
 *
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String type;

    @NotNull
    private long preference;

    @CreationTimestamp
    private Instant added;

    @UpdateTimestamp
    private Instant located;

    @OneToOne(targetEntity = Room.class)
    private Room room;

    @OneToMany(targetEntity = Favourite.class)
    private List<Favourite> favouriteRooms;

    /**
     * Creates AnimalResponseDto
     *
     * @return animalResponseDto AnimalResponseDto
     */
    public AnimalResponseDto toAnimalResponseDto() {
        return new AnimalResponseDto(this.title, this.added, this.located);
    }

}
