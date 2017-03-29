package com.android.davidgj.cajeroautomaticose.lib;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Thanos on 11/1/2017.
 */

//CON ESTA CLASE NOS GARANTIZAMOS QUE CUANDO LAS COSAS CAMBIEN DE LA LIBRERIA EVENTBUS O QUIERA UTILIZAR OTRA MI CODIGO SUFRIRA CAMBIOS MENORES
// PORQUE UNICAMENTE TENDREMOS Q CAMBIAR ESTA IMPLEMENTACION YA QUE LA INTERFACE QUE DEFINE COMO TRABAJA EVENTBUS TRABAJA
//EVENTBUS SE QUEDA EZACTAMENTE IGUAL

//CLASE Q IMPLEMENTA LA INTERFACE EVENTBUSHELPER QUE ES UN ENVOLTORIO DE EVENTBUS
public class GreenRobotEventBusHelperImple implements EventBusHelper {

    //DECLARAMOS UN OBJETO DEL TIPO EVENTBU, CON ESTA VARIABLE ACCEDEMOS A TODO LO Q NECESITAMOS DE LA LIBRERIA EVENTBUS
    EventBus eventBus;

    //TRABAJAREMOS ESTA VARIABLE COMO SINGLETON PARA TENER SOLO UNA VARIABLE DE GreenRobotEventBusHelperImple EN TODA LA APLICACION
    //CLASE QUE TIENE SOLO UNA VARIABLE INSTANCIADA
    private static class SingletonHolder{
        private static final GreenRobotEventBusHelperImple INSTANCE = new GreenRobotEventBusHelperImple();
    }

    //METODO ESTATICO PARA OBTENER SOLO UNA INSTANCIA DE ESTA CLASE GreenRobotEventBusHelperImple
    public static GreenRobotEventBusHelperImple getInstance(){
        return SingletonHolder.INSTANCE;
    }

    //CONTRUCTOR PARA INICIALIZAR EVENTBUS CON EL BUS POR DEFECTO ESTE CONSTRUCTOR SERA LLAMADO SOLO UNA VES
    //GRACIAS A LA PALABRA FINAL DE LA CLASE SingletonHolder
    public GreenRobotEventBusHelperImple() {
        this.eventBus = EventBus.getDefault();
    }

    //LLAMAMOS A LOS METODOS DE LA LIBRERIA DE EVENTBUS CON LA VARIABLE eventbus
    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    //LLAMAMOS A LOS METODOS DE LA LIBRERIA DE EVENTBUS CON LA VARIABLE eventbus
    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    //LLAMAMOS A LOS METODOS DE LA LIBRERIA DE EVENTBUS CON LA VARIABLE eventbus
    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
