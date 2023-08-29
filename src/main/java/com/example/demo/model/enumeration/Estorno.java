package com.example.demo.model.enumeration;

public enum Estorno {

    ESTORNADO(1),
    NAO_ESTORNADO(2),

    NAO_SOLICITADO(3);

    private int codigo;

    private Estorno(int codigo){
        this.codigo = codigo;
    }

    public int getCodigo(){
        return codigo;
    }

    public static  Estorno valueOf(int codigo) {
        for (Estorno value : Estorno.values()) {
            if (value.getCodigo() == codigo) {
                return value;
            }
        }

        throw new IllegalArgumentException("código inválido");
    }
}
