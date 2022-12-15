package service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public T findByName(Object namedQuery, Object parameterName, Object name) {
        Query query = getEntityManager().createNamedQuery((String) namedQuery);
        List<T> results = query.setParameter((String) parameterName, (String) name).getResultList();
        if(results.size() > 0) {
            return results.get(0);
        }
        return null;
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

}
