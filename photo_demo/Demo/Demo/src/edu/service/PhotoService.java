package edu.service;

import edu.bean.Photo;
import edu.bean.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;


public class PhotoService {
    private HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public void save(Photo photo){
        template.save("Photo",photo);
    }

    public Photo getById(int id){
        return template.get(Photo.class,id);
    }

    public void remove(Photo photo){
        template.delete(photo);
    }

    public void remove(final int id){
        template.execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                Query q = session.createQuery("delete from Photo where id = :id");
                q.setInteger("id",id);
                return q.executeUpdate();
            }
        });
    }

    public PageResult findByCatalog(final int creator,final String catalog, final int start, final int limit){
        return template.execute(new HibernateCallback<PageResult>() {
            @Override
            public PageResult doInHibernate(Session session) throws HibernateException {
                PageResult result = new PageResult();

                Query q = session.createQuery("select count(*) from Photo where catalog=:catalog and creator=:creator");
                q.setString("catalog",catalog);
                q.setInteger("creator",creator);
                long totals = (long)q.uniqueResult();
                result.setTotals(totals);

                if(totals > 0){
                    q = session.createQuery("from Photo where catalog=:catalog and creator=:creator order by createDt desc");
                    q.setString("catalog",catalog);
                    q.setInteger("creator",creator);
                    q.setMaxResults(limit);
                    q.setFirstResult(start);
                    result.setItems(q.list());
                }
                return result;
            }
        });
    }

    public PageResult findByQuery(final int creator,final String catalog,final String query, final int start, final int limit){
        return null;
    }

}
