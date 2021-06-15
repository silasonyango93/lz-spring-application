package livelihoodzone.dao.reports.zonal.wealthgroup;

import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.repository.questionnaire.county.LzQuestionnaireSessionRepository;
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
public class LzQuestionnaireSessionDao implements LzQuestionnaireSessionRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<LzQuestionnaireSessionEntity> findByLzQuestionnaireUniqueId(String lzQuestionnaireUniqueId) {
        return null;
    }

    @Override
    public List<LzQuestionnaireSessionEntity> fetchQuestionnaireSessionByCountyAndLivelihoodZone(int countyId, int livelihoodzoneId) {
        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = manager.createNamedQuery("LzQuestionnaireSessionEntity.fetchQuestionnaireSessionByCountyAndLivelihoodZone", LzQuestionnaireSessionEntity.class)
                .setParameter(1, countyId)
                .setParameter(2, livelihoodzoneId)
                .getResultList();
        return lzQuestionnaireSessionEntityList;
    }

    @Override
    public List<LzQuestionnaireSessionEntity> findAll() {
        return null;
    }

    @Override
    public List<LzQuestionnaireSessionEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<LzQuestionnaireSessionEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<LzQuestionnaireSessionEntity> findAllById(Iterable<Long> iterable) {
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
    public void delete(LzQuestionnaireSessionEntity lzQuestionnaireSessionEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends LzQuestionnaireSessionEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends LzQuestionnaireSessionEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends LzQuestionnaireSessionEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<LzQuestionnaireSessionEntity> findById(Long aLong) {
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
    public <S extends LzQuestionnaireSessionEntity> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<LzQuestionnaireSessionEntity> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public LzQuestionnaireSessionEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends LzQuestionnaireSessionEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends LzQuestionnaireSessionEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends LzQuestionnaireSessionEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends LzQuestionnaireSessionEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends LzQuestionnaireSessionEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends LzQuestionnaireSessionEntity> boolean exists(Example<S> example) {
        return false;
    }
}
