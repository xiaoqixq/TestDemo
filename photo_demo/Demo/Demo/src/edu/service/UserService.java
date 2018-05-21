package edu.service;

import edu.bean.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;


public class UserService {
    private HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public User getUserByLoginName(final String loginName){
        return template.execute(new HibernateCallback<User>() {
            @Override
            public User doInHibernate(Session session) throws HibernateException {
                Query q = session.createQuery("from User where loginName=:loginName");
                q.setString("loginName",loginName);
                return (User) q.uniqueResult();
            }
        });
    }
}
