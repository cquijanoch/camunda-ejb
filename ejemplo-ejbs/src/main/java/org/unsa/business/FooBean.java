package org.unsa.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class FooBean implements Foo {

    @EJB
    private Bar bar;

    @Override
    public String decirOtraCosa() {
        return "Foo dice  " + bar.decirHola();
    }

}
