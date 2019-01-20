package com.orm.udemy.mysql.repos.entities.sameid.queued;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.UUID;

@Entity(name = "movie_details_queued_uuid_assigned")
@Table(name = "movie_details_queued_uuid_assigned")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDetails implements IdentifiedDataSerializable {

    @Id
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Movie movie;

    private Long usGross;
    private Long worldwideGross;
    private Long productionBudget;
    private String releaseDate;
    private String mpaaRating;
    private Integer runningTimeMin;
    private String distributor;
    private String source;
    private String majorGenre;
    private String creativeType;
    private String director;
    private String rottenTomatoesRating;
    private Double imdbRating;
    private Long imdbVotes;


    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(id.toString());
        out.writeLong(usGross);
        out.writeLong(worldwideGross);
        out.writeLong(productionBudget);
        out.writeUTF(releaseDate);
        out.writeUTF(mpaaRating);
        out.writeInt(runningTimeMin);
        out.writeUTF(distributor);
        out.writeUTF(source);
        out.writeUTF(majorGenre);
        out.writeUTF(creativeType);
        out.writeUTF(director);
        out.writeUTF(rottenTomatoesRating);
        out.writeDouble(imdbRating);
        out.writeLong(imdbVotes);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        id = UUID.fromString(in.readUTF());
        usGross = in.readLong();
        worldwideGross = in.readLong();
        productionBudget = in.readLong();
        releaseDate = in.readUTF();
        mpaaRating = in.readUTF();
        runningTimeMin = in.readInt();
        distributor = in.readUTF();
        source = in.readUTF();
        majorGenre = in.readUTF();
        creativeType = in.readUTF();
        director = in.readUTF();
        rottenTomatoesRating = in.readUTF();
        imdbRating = in.readDouble();
        imdbVotes = in.readLong();
    }

    @Override
    public int getFactoryId() {
        return HazelcastConfig.DataSerializableFactoryImpl.ID;
    }

    @Override
    public int getId() {
        return HazelcastConfig.DataSerializableFactoryImpl.MOVIE_DETAILS_TYPE;
    }
}
