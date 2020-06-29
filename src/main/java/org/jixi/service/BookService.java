package org.jixi.service;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.jixi.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * 组件BookService需要用到另一个组件BookDao
 *
 * 1. @Autowired自动注入，把组件BookDao拿过来，如果找到就赋值。
 *
 * 2. 默认优先按照类型去容器中找对应的组件，类似application.getBean(BookDao.class);
 *
 * 3. 如果在容器中找到多个同类型的bean组件，那再按照BookService类中属性的名字作为组件的id去容器中查找;类似application.getBean("bookDao");
 *
 * 4. @Qualifier("bookDao1") 指定需要装配的组件的id，而不是使用属性名
 *
 * 5. 自动装配时，默认一定要让BookDao类（组件）的属性赋值好，即去掉@Repository和@Bean方式--无BookDao组件），否则报错。
 *    如果避免报错，可以使用@Autowired(required = false) 这时属性bookDao值为null
 *
 * 6. @Primary,让spring进行自动装配时，默认使用首选的bean，如果有@Qualifier注解的话，优先选择指定的@Qualifier
 *
 * 7. @Resource(name = "bookDao1”)  也可以实现自动注入  默认是按照组件名称（属性名）进行装配的
 *    不支持@Primary功能、没有支持@Autowired(required = false)
 *
 * 8.  @Inject 也可以实现自动注入,需要导入依赖javax.inject， 和 @Autowired功能一样,但是没有required = false功能
 */
@Service
public class BookService {

    @Qualifier("bookDao")
//    @Autowired
    @Autowired(required = false)

    @Resource(name = "bookDao1")
    @Inject
    private BookDao bookDao;
//    private BookDao bookDao1;

    public void printBookDao(){
        System.out.println(bookDao);
    }
}
