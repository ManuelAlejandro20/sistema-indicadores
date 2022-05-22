/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.services;

import com.ua.sistemaindicadores.backend.exceptions.NotificacionCorreoException;

import java.io.UnsupportedEncodingException;

import javax.activation.DataHandler;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 *
 *
 * @author guilberto
 *
 */
@Stateless

@SecurityDomain("DvcmeCredencialesDomain")
@PermitAll

public class CorreoService {

    @Resource(mappedName = "java:jboss/mail/Dvcme")
    private Session session;

    @Resource(name = "remitenteCorreo")
    private String remitenteCorreo;
    public String linkSistemaIndicadores = "http://localhost:8080/SistemaIndicadores-1.0-SNAPSHOT/";

    public void enviarMensajeTexto(String destinatarios, String titulo, String mensaje, String correoAdmin) throws NotificacionCorreoException {

        if (destinatarios == null || destinatarios.isEmpty()
                || titulo == null || titulo.isEmpty()
                || mensaje == null || mensaje.isEmpty()
                || correoAdmin == null || correoAdmin.isEmpty()) {

            throw new NotificacionCorreoException("No se pudo notificar por email al destinatario: " + destinatarios);

        }

        try {

            Message message = new MimeMessage(session);

            //message.setFrom(new InternetAddress(remitenteCorreo, "Gestión de cuentas"));
            message.setFrom(new InternetAddress("SOPORTE.DVCME@uantof.cl", "Gestión de cuentas"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoAdmin));

            message.addRecipient(Message.RecipientType.CC, new InternetAddress(destinatarios));

            message.setSubject(titulo);

            message.setContent(mensaje, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException | UnsupportedEncodingException ex) {

            throw new NotificacionCorreoException("No se pudo notificar por email al destinatario: " + destinatarios, ex);

        }

    }

    // @PermitAll
    public void enviarMensajeTexto(String destinatario, String titulo, String mensaje) throws NotificacionCorreoException {

        if (destinatario == null || destinatario.isEmpty()
                || titulo == null || titulo.isEmpty()
                || mensaje == null || mensaje.isEmpty()) {

            throw new NotificacionCorreoException("No se pudo notificar por email al destinatario: " + destinatario);

        }

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("SOPORTE.DVCME@uantof.cl", "Sistema de Indicadores"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario.trim()));

            message.setSubject(titulo);

            message.setContent(mensaje, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException | UnsupportedEncodingException ex) {

            throw new NotificacionCorreoException("No se pudo notificar por email al destinatario: " + destinatario, ex);

        }

    }

    public void enviarMensajeTextoEmpresa(String destinatario, String titulo, String mensaje) throws NotificacionCorreoException {

        if (destinatario == null || destinatario.isEmpty()
                || titulo == null || titulo.isEmpty()
                || mensaje == null || mensaje.isEmpty()) {

            throw new NotificacionCorreoException("No se pudo notificar por email al destinatario: " + destinatario);

        }

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("SOPORTE.DVCME@uantof.cl", "Sistema de Indicadores"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario.trim()));

            message.setSubject(titulo);

            message.setContent(mensaje, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException | UnsupportedEncodingException ex) {

            throw new NotificacionCorreoException("No se pudo notificar por email al destinatario: " + destinatario, ex);

        }

    }

    public void enviarMensajeBinario(String cc, String titulo, String mensaje, String to, byte[] arr, String mimeType, String attachment) throws NotificacionCorreoException {

        if (to == null || to.isEmpty()
                || cc == null || cc.isEmpty()
                || titulo == null || titulo.isEmpty()
                || mensaje == null || mensaje.isEmpty()
                || arr == null || arr.length == 0
                || mimeType == null || mimeType.isEmpty()
                || attachment == null || attachment.isEmpty()) {

            throw new NotificacionCorreoException("No se pudo notificar por email al destinatario: " + to);

        }

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(remitenteCorreo, "Gestión de cuentas"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));

            message.setSubject(titulo);

            BodyPart textPart = new MimeBodyPart();

            textPart.setContent(mensaje, "text/html; charset=utf-8");

            BodyPart filePart = new MimeBodyPart();

            filePart.setDataHandler(new DataHandler(new ByteArrayDataSource(arr, mimeType)));

            filePart.setFileName(attachment);

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(textPart);

            multipart.addBodyPart(filePart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException | UnsupportedEncodingException ex) {

            throw new NotificacionCorreoException("No se pudo notificar por email al destinatario: " + to, ex);

        }

    }

    public String getLinkSistemaIndicadores() {
        return linkSistemaIndicadores;
    }

}
