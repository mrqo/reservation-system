package booking;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointRepository extends CrudRepository<Endpoint, Long> {
    List<Endpoint> findByOrderByDateAsc();
}