package livelihoodzone.repository.user_management;

import livelihoodzone.entity.questionnaire.county.LzHazardsEntity;
import livelihoodzone.entity.user_management.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    public Roles findByRoleCode(@Param("RoleCode") int roleCode);

    public Roles findByRoleId(@Param("RoleId") int roleId);
}
