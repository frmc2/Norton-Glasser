package com.fatec.consultorio.atv27_02;

public class Exame {
    private String consulta;
    private String data;
    private String descritivo;



    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescritivo()  {
        return descritivo;
    }

    public void setDescritivo(String descritivo) throws Exception{
        if(descritivo==null || descritivo.isBlank()){
            throw new Exception("Ocorreu um erro. O descritivo não pode ser vazio!");
        }
        else this.descritivo = descritivo;
    }

    void solicitar(){

    }

    void consultar(){

    }



    public Exame(){
        this.consulta = "";
        this.data ="";
        this.descritivo = "";
    }



    public Exame(String pConsulta, String pData, String pDescritivo)throws Exception {
        setConsulta(pConsulta);
        setData(pData);
        setDescritivo(pDescritivo);
    }

    void mostrar(){
        System.out.println("---Exame---");
        System.out.println("Consulta: "+getConsulta());
        System.out.println("Data: "+getData());
        System.out.println("descritivo: "+getDescritivo()+ "\n");
    }
}