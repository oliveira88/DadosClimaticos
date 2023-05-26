package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.view.MaximasMinimas;


public class MaximasMinimasPresenter {

   
    private final MaximasMinimas view;

    public MaximasMinimasPresenter() {
        this.view = new MaximasMinimas();
        this.view.setVisible(true);
    }
    
     public MaximasMinimas getView() {
        return view;
    }
}

