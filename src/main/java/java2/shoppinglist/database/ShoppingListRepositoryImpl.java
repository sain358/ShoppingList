package java2.shoppinglist.database;

import java2.shoppinglist.database.ShoppingListRepository;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.domains.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
class ShoppingListRepositoryImpl implements ShoppingListRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ShoppingList shoppingList) {
        sessionFactory.getCurrentSession().save(shoppingList);
    }

    @Override
    public void remove(ShoppingList shoppingList) {
        sessionFactory.getCurrentSession().delete(shoppingList);
    }


    @Override
    public Optional<ShoppingList> findByUserAndTitle(User user, String title) {
        String query = "from ShoppingList sl where sl.title = :title and sl.user = :user";
        ShoppingList shoppingList = (ShoppingList) sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("title", title)
                .setParameter("user", user)
                .uniqueResult();
        return Optional.ofNullable(shoppingList);
    }

    @Override
    public List<ShoppingList> findShoppingLists(User user) {
        String query = "from ShoppingList sl where sl.user = :user";
        return sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("user", user)
                .list();
    }


}
