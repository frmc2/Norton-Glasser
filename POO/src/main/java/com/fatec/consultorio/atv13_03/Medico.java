package com.fatec.consultorio.atv13_03;


public class Medico extends Funcionario {

    private String crm;
    private String especialidade;

    public String getCrm(){
        return crm;
    }

    public void setCrm(String crm) throws Exception{
        if(crm==null || crm.isBlank()){
            throw new Exception("Ocorreu um erro. O crm não pode ser vazio!");
        }
        else this.crm = crm;
    }

    public String getEspecialidade(){
        return especialidade;
    }

    public void setEspecialidade(String especialidade){
        this.especialidade = especialidade;
    }

    public Medico(){
        this.crm="";
        this.especialidade="";
    }

    public Medico(String pNome,String pCrm, String pTelefone, String pEspecialidade,String pSenha)throws Exception{
        setNome(pNome);
        setCrm(pCrm);
        setTelefone(pTelefone);
        setEspecialidade(pEspecialidade);
        setSenha(pSenha);
    }


    void mostrar(){
        System.out.println("---Médico---");
        System.out.println("Nome: "+getNome()+"\ncrm: "+getCrm()+"\ntelefone: "+getTelefone()+"\nespecialidade: "+getEspecialidade()+"\nsenha: "+getSenha()+"\n");
    }

    @Override
    public void acessar() {
        System.out.println("Acesso ao prontuário médico liberado para o Dr(a). " + getNome());
    }

}
