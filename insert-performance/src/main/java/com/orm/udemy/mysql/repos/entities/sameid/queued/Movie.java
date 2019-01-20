package com.orm.udemy.mysql.repos.entities.sameid.queued;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.UUID;

@Entity(name="movie_queued_uuid_assigned")
@Table(name="movie_queued_uuid_assigned")
@NoArgsConstructor
@Data
public class Movie implements IdentifiedDataSerializable {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL,
              fetch = FetchType.LAZY, optional = false)
    private MovieDetails movieDetails;

    private String title;

    private String shortDescription;

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(id.toString());
        out.writeUTF(title);
        out.writeUTF(shortDescription);
        out.writeObject(movieDetails);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.id = UUID.fromString(in.readUTF());
        this.title = in.readUTF();
        this.shortDescription = in.readUTF();
        this.movieDetails = in.readObject(MovieDetails.class);
    }

    @Override
    public int getFactoryId() {
        return HazelcastConfig.DataSerializableFactoryImpl.ID;
    }

    @Override
    public int getId() {
        return HazelcastConfig.DataSerializableFactoryImpl.MOVIE_TYPE;
    }

}
