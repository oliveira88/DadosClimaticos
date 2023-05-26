
package com.ufes.dadosclimaticos.presenter;

import com.ufes.dadosclimaticos.view.DadosMedios;


public class DadosMediosPresenter {
    private DadosMedios view;

    public DadosMediosPresenter() {
        this.view = new DadosMedios();
        this.view.setVisible(true);
    }

    public DadosMedios getView() {
        return view;
    }
}
