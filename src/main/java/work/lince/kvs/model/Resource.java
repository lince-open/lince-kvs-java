package work.lince.kvs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import work.lince.commons.persistence.model.LastChangeDetail;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_RESOURCE", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "key"})
        , indexes = {})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @JsonIgnore
    @Id
    @Column(name = "ID")
    private String id;

    @NotNull
    @Column(name = "TTL")
    private Long ttl;

    @NotNull
    @Column(name = "NAME", length = 50)
    private String name;

    @NotNull
    @Column(name = "KEY", length = 50)
    private String key;

    @Lob
    @Column(name = "VALUE")
    private String value;

    @Embedded
    private LastChangeDetail lastChange;

}
