package br.app.wil.backend.service;

public class SMSNotificacaoService implements NotificacaoService {

    @Override
    public void enviarNotificacao(String mensagem, String destinatario) {
        System.out.println("Enviando SMS para " + destinatario + " com a mensagem: " + mensagem);
    }
}
