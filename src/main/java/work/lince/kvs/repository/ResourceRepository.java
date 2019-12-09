package work.lince.kvs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.lince.kvs.model.Resource;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, String> {

    List<Resource> findByName(String name);

    Optional<Resource> findByNameAndKey(String name, String key);

    Long deleteByName(String name);

    Long deleteByNameAndKey(String name, String key);


}

