/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pilas;

/**
 *
 * @author Pablo
 */
public class PilaA <T> implements PilaADT<T> {
    private T[] pila;
    private int tope;
    private final int MAXIMO = 20;
    
    public PilaA(){
        pila = (T[]) new Object [MAXIMO];
        tope = -1; //porque tope representa el índice. Un indice negativo indica que no hay nada, y al poner tope++ indica un indice 0
    }

    public PilaA(int max){
        pila = (T[]) new Object [max];
        tope = -1; //Indica que la pila esta vacia
    }

    @Override
    public void push(T dato) {
        if(tope == pila.length - 1)//Indica que la pila está llena
            expande();
        tope++;
        pila[tope]=dato;
    }
    
    private void expande(){
        T[] masGrande = (T[]) new Object [pila.length * 2];
        
        for(int i = 0; i <= tope; i++)//o bien: i < pila.length
            masGrande[i]=pila[i];
        pila = masGrande;
    }

    @Override
    public T pop() {
        if(this.isEmpty())
            throw new ExcepcionColeccionVacia("Error: la pila esta vacia");
        T resultado = pila[tope];
        pila[tope]=null;
        tope--;
        return resultado;
    }

    @Override
    public boolean isEmpty() {
        return tope == -1;
    }

    @Override
    public T peek() {
        if(this.isEmpty())
            throw new ExcepcionColeccionVacia("Error: la pila esta vacia");
        return pila[tope];
    }
    
    @Override
    public T[] multiPop(int n) {
        T[] arre = null;
        if(n >= 0 && n<= tope + 1){
            arre = (T[]) new Object[n];
            for(int i = 0; i <n ; i++){
                    arre[i] = pila[tope];
                    pila[tope]=null;
                    tope--;
                }
        }
        return arre;
    }
    
    @Override
    public String toString(){
        StringBuilder cad = new StringBuilder();
        for(int i=0;i<tope+1;i++){
            cad.append(pila[i]);
            cad.append(".");
        }
        return cad.toString();
    }


    
    
}
