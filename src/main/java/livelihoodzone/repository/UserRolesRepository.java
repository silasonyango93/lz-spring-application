package livelihoodzone.repository;

import livelihoodzone.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
    public List<UserRoles> findByUserId(@Param("UserId") int userId);
}
