package livelihoodzone.repository.questionnaire.calendar;

import livelihoodzone.entity.questionnaire.calendar.MonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MonthsRepositrory extends JpaRepository<MonthsEntity, Long> {
    public MonthsEntity findByMonthId(@Param("MonthId") int monthId);
}
