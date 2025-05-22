package org.punto1a.Dominio;

public class Nota<T> {
    private T valor;

    public Nota(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
}
