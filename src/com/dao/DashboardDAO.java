package com.dao;

import com.controller.OperadorJpaController;

/**
 *
 * @author keatnis
 */
public class DashboardDAO {
    private FleteDAO fleteDAO;
    private OperadorJpaController operadorJpaController;

    public DashboardDAO() {
        this.fleteDAO = new FleteDAO();
        this.operadorJpaController = new OperadorJpaController();
    }
    
    public void getDashboardFlete(){
        fleteDAO.getDataFlete();
    }
}
