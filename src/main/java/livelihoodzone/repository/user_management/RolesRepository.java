package livelihoodzone.repository.user_management;

import livelihoodzone.entity.user_management.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
}
