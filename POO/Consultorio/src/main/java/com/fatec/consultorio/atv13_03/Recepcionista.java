package com.fatec.consultorio.atv13_03;


import java.time.LocalDate;
import java.time.LocalTime;

public class Recepcionista extends Funcionario {
    private String cpf;

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf) throws Exception{
        if(cpf==null || cpf.isBlank() || cpf.length() !=11){
            throw new Exception("Ocorreu um erro. O CPF digitado está no formato incorreto!");
        }
        else {
            cpf= cpf.replace(".", "").replace("-","");
            this.cpf = cpf;
        }
    }

    public Recepcionista(){
        this.cpf="";
    }

    public Recepcionista(String pNome,String pCpf,String pTelefone,String pSenha)throws Exception{
        setNome(pNome);
        setCpf(pCpf);
        setTelefone(pTelefone);
        setSenha(pSenha);
    }

    void mostrar(){
        System.out.println("----Recepcionista---");
        System.out.println("Nome: "+getNome()+"\nCPF: "+getCpf()+"\nTelefone: "+getTelefone()+"\nSenha: "+getSenha()+"\n");
    }

    @Override
    public void acessar() {
        System.out.println("Acesso ao sistema de agendamento liberado para a recepção: " + getNome());
    }

    public Agenda marcarAgenda(Paciente p, Medico m, LocalTime hora, LocalDate data) throws Exception {

        var a1 = new Agenda();
        a1.setData(data);
        a1.setHora(hora);
        a1.setMedico(m);
        a1.setPaciente(p);

        return a1;
    }
}
