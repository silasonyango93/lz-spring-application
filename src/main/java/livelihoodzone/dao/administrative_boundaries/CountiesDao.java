package livelihoodzone.dao.administrative_boundaries;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
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
public class CountiesDao implements CountiesRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public CountiesEntity findByCountyId(int countyId) {
        return null;
    }

    @Transactional
    @Override
    public List<CountiesEntity> fetchCountyComprehensively(int countyId) {
        List<CountiesEntity> countiesEntityList = manager.createNamedQuery("CountiesEntity.fetchCountyComprehensively", CountiesEntity.class)
                .setParameter(1, countyId)
                .getResultList();
        return countiesEntityList;
    }

    @Override
    public List<CountiesEntity> findAll() {
        return null;
    }

    @Override
    public List<CountiesEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CountiesEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CountiesEntity> findAllById(Iterable<Long> iterable) {
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
    public void delete(CountiesEntity countiesEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends CountiesEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CountiesEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends CountiesEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CountiesEntity> findById(Long aLong) {
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
    public <S extends CountiesEntity> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<CountiesEntity> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CountiesEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends CountiesEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CountiesEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CountiesEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CountiesEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CountiesEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CountiesEntity> boolean exists(Example<S> example) {
        return false;
    }
}
