/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadora;

import java.util.ArrayList;
import pilas.PilaA;

/**
 *
 * @author Mariela Mejia
 */
public class Calculadora {
  
    /**
     * Imprime ArrayList como cadena String
     * @param arre ArrayList de String
     * @return devuelve como cadena de string los valores que recibe de la lista
     */
    public static String imprimeArray(ArrayList<String> arre){
        StringBuilder bobTheBuilder = new StringBuilder();
        for(int i = 0; i < arre.size(); i++)
            bobTheBuilder.append(arre.get(i));
        return bobTheBuilder.toString();
    }
    
    /**
     * Elimina el ultimo elemento del String dado
     * @param s String a eliminar
     * @return Sting eliminado
     */
    public static String quitarUltimoElemento(String s){
        String res;
        if(!s.isEmpty())
            res = s.substring(0,s.length()-1);
        else
            res = s;
        return res; 
    }
    /**
     * Identifica si el valor dado es positivo o negativo
     * @param s Recibe un valor numerico
     * @return identifica si es negativo o positivo, lo devuelve
     */
    public static String cambiaDeSigno(String s){
        String res;
        //caso 1: pasar de - a + (que ya haya un -)
        if(String.valueOf(s.charAt(0)).equals("-"))
            res = s.substring(1, s.length());
         //caso 2: pasar de + a -
        else
            res = "-"+s;            
        return res;
    }
    /**
     * Recibe el ArrayList para analizar que los parentesis esten balanceados correctamente
     * @param arr 
     * @return Estan correctamente balanceados
     */
    private static boolean revisaCadena(ArrayList<String> arr){
        PilaA<String> pila = new PilaA();
        boolean res = true;
        int cont = 0;
        String letra;
        
        while(cont < arr.size() && res){
            letra = arr.get(cont);
            if(letra.equals("(")){
                pila.push(letra);
            }
            else{
                if(letra.equals(")")){
                    if(!pila.isEmpty()){
                        pila.pop();
                    }
                    else{
                        res = false;
                    }
                }
            }
            cont++;
        }
        
        if(res && !pila.isEmpty()){
            res = false;
        }
        return res;
    }
    /**
     * Identifica si el valor dado es punto o no
     * @param elemento Recibe un valor en string
     * @return es valor numerico/operador o punto decimal
     */
    private static boolean revisaPuntoDecimal(String elemento){
        boolean res = true;
        int cont = 0;
        int i = 0;
        String numero;
        
        if (elemento.length()==1 && elemento.substring(i).equals(".")){
            res = false;
        }
        else{
            if (elemento.substring(i,i+1).equals("-") && elemento.substring(i+1,i+2).equals(".")){
                res = false;
            }
            else{
                while(res && i<elemento.length()){
                    numero = elemento.substring(i,i+1);
                    if (numero.equals(".")){
                        cont++;
                    }
                    if (cont>1){
                        res = false;
                    }
                    i++;
                }
            }
        }
        return res;
    }
    /**
     * Valida si la sintaxis de la lista es correcta
     * @param arr recibe ArrayList String
     * @return Es correcta o incorrecta
     */
    public static boolean revisaArray(ArrayList<String> arr){
        boolean res = true;
        int i = 0;
        ArrayList<String> operadores = new ArrayList();
        boolean bandera = true;
        
        operadores.add("+");
        operadores.add("*");
        operadores.add("-");
        operadores.add("^");
        operadores.add("/");
        if (arr.isEmpty()){
            res = false;
        }
        else{
            if(!revisaCadena(arr)){
                res = false;
            }
            else{
                if(operadores.contains(arr.get(0)) || arr.get(0).equals(")")){
                    res = false;
                }
                else{
                    if(operadores.contains(arr.get(arr.size()-1)) || arr.get(arr.size()-1).equals("(")){
                        res = false;
                    }
                }
            }
        }
        operadores.add("(");
        while(i < arr.size()&& res){
            switch(arr.get(i)){
                case"+" -> {
                    if (operadores.contains(arr.get(i-1))){
                        res= false;
                    }
                    bandera = true;
                }
                case"-" -> {
                    if (operadores.contains(arr.get(i-1))){
                        res= false;
                    }
                    bandera = true;
                }
                case"*" -> {
                    if (operadores.contains(arr.get(i-1))){
                        res= false;
                    }
                    bandera = true;
                }
                case"/" -> {
                    if (operadores.contains(arr.get(i-1))){
                        res= false;
                    }
                    bandera = true;
                }
                case"^" -> {
                    if (operadores.contains(arr.get(i-1))){
                        res= false;
                    }
                    bandera = true;
                }
                case"(" -> {
                    if (i > 0 && arr.get(i-1).equals(")") ){
                        res= false;
                    }
                    if (bandera == false){
                        res = false;
                    }
                    bandera = true;
                }
                case")" -> {
                    if (operadores.contains(arr.get(i-1))){
                        res= false;
                    }
                }
                default -> {
                    if (!revisaPuntoDecimal(arr.get(i))){
                        res = false;
                    }
                    if (bandera ==false){
                        res = false;
                    }
                    bandera = false;
                }
            }
            i++;
        }
        return res;
    }
    /**
     * Valida si el dato recibido es un Operador
     * @param c String tomado
     * @return es valor numerico u operador
     */
    public static boolean esOperador(String c){
        boolean res=false;
        if( c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^"))
            res = true;
        return res;
    }
    /**
     * Ordena la jerarquia de operaciones
     * @param operador Recibe un string con el operador utilizado (+,-,*,/)
     * @return valor numerico asignado a cada operador
     */
    private static int prioridadOperadores(String operador){
        int res;
        res = switch (operador) {
            case ")" -> 0;
            case "+" -> 1;
            case "-" -> 1;
            case "*" -> 2;
            case "/" -> 2;
            case "^" -> 3;
            default -> -1;
        };   
        return res;
    }
    
    /**
     * Metodo que reescribe valores dados en forma infija a la forma postfija
     * @param infijo ArrayList de tipo String
     * @return devuelve un ArrayList en metodo postfijo
     */
    public static ArrayList<String> escribePostfijo(ArrayList<String> infijo){
        ArrayList<String> postfija = new ArrayList();
        PilaA<String> operadores = new PilaA();
        for(int i = 0; i < infijo.size(); i++){
            if(!esOperador(infijo.get(i)) && !infijo.get(i).equals("(") && !infijo.get(i).equals(")"))
                postfija.add(infijo.get(i));
            if(esOperador(infijo.get(i))){
                while(!operadores.isEmpty() && prioridadOperadores(infijo.get(i))<= prioridadOperadores(operadores.peek()))
                    postfija.add(operadores.pop());
                operadores.push(infijo.get(i));
            }
            if(infijo.get(i).equals("("))
                operadores.push(infijo.get(i));
            if(infijo.get(i).equals(")")){
                while(!operadores.peek().equals("("))
                    postfija.add(operadores.pop());
                operadores.pop();
            }
        }
        while(!operadores.isEmpty())
            postfija.add(operadores.pop());
        return postfija;
    }
    
}
