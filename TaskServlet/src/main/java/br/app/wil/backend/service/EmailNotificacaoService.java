package br.app.wil.backend.service;

public class EmailNotificacaoService implements NotificacaoService {

    @Override
    public void enviarNotificacao(String mensagem, String destinatario) {
        System.out.println("Enviando e-mail para " + destinatario + " com a mensagem: " + mensagem);
    }
}
