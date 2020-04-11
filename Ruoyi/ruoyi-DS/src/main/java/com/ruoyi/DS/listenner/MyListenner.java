package com.ruoyi.DS.listenner;

import com.ruoyi.DS.service.impl.RandomImpl;
import com.ruoyi.DS.utils.OrderNumberBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class MyListenner implements ServletContextListener {
    @Autowired
    private RandomImpl random;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        OrderNumberBuilder.setRANDOMNUMER(random.getNumber());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        random.setNumber(OrderNumberBuilder.getRANDOMNUMER());
    }
}
