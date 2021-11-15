package livelihoodzone.dao.reports.zonal.wealthgroup;

import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.repository.questionnaire.county.LzQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class WgQuestionnaireSessionDao implements WgQuestionnaireSessionRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<WgQuestionnaireSessionEntity> findByQuestionnaireUniqueId(String questionnaireUniqueId) {
        return null;
    }

    @Override
    public List<WgQuestionnaireSessionEntity> findByCountyIdAndWgQuestionnaireTypeId(int countyId, int wgQuestionnaireTypeId) {
        return null;
    }

    @Override
    public List<WgQuestionnaireSessionEntity> fetchQuestionnaireSessionsByCountyAndWealthGroup(int countyId, int wealthGroupId) {
        List<WgQuestionnaireSessionEntity> wgQuestionnaireSessionEntityList = manager.createNamedQuery("WgQuestionnaireSessionEntity.fetchQuestionnaireSessionsByCountyAndWealthGroup", WgQuestionnaireSessionEntity.class)
                .setParameter(1, countyId)
                .setParameter(2, wealthGroupId)
                .getResultList();
        return wgQuestionnaireSessionEntityList;
    }

    @Override
    public List<WgQuestionnaireSessionEntity> findAll() {
        return null;
    }

    @Override
    public List<WgQuestionnaireSessionEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<WgQuestionnaireSessionEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<WgQuestionnaireSessionEntity> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(WgQuestionnaireSessionEntity wgQuestionnaireSessionEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends WgQuestionnaireSessionEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends WgQuestionnaireSessionEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends WgQuestionnaireSessionEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<WgQuestionnaireSessionEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends WgQuestionnaireSessionEntity> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<WgQuestionnaireSessionEntity> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public WgQuestionnaireSessionEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends WgQuestionnaireSessionEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends WgQuestionnaireSessionEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends WgQuestionnaireSessionEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends WgQuestionnaireSessionEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WgQuestionnaireSessionEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends WgQuestionnaireSessionEntity> boolean exists(Example<S> example) {
        return false;
    }
}
