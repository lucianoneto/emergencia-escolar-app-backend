# 🚨📱 Emergência Escolar - Backend
Esta aplicação Java é um protótipo de emergência escolar desenvolvido no Hackathon Campus Party Goiás 2023, que permite aos usuários enviar pedidos de emergência ou denúncias anônimas para uma equipe de atendimento. 
A aplicação recebe dados de localização, ou dados da denúncia junto com arquivos (fotos, vídeos, audios, etc) por meio de uma API REST e envia notificações por E-mail e WhatsApp para a equipe de atendimento.

## 💻 Tecnologias

No desenvolvimento dessa aplicação foi utilizado: 

- Java 17
- Maven
- Spring Boot
- Spring Boot Starter Mail
- Spring Web Services
- Lombok
- Twilio
- Jackson

## ❔ Como usar a aplicação

- Clone o repositório para sua máquina local.
- Abra o projeto em sua IDE Java preferida.
- Configurar as seguintes variáveis de ambiente no seu .env:

  `EMAIL_SENDER` - E-mail remetente.

  `EMAIL_PASSWORD` - Senha do e-mail remetente.

  `EMAIL_RECEIVER` - E-mail destinatário.

  `MAIL_TRUST_HOST` - Servidor do e-mail utilizado.

  `MAIL_PORT` - Porta do servidor do e-mail utilizado.

  `MAIL_AUTH` - Autenticação SMTP habilitada ou não.

  `MAIL_STARTTLS_ENABLE` - Protocolo STARTTLS habilitado ou não.

  `MAIL_STARTTLS_REQUIRED` - Protocolo STARTTLS obrigatório ou não.

  `TWILIO_SID` - Sid da conta Twilio.

  `TWILIO_TOKEN` - Token da conta Twilio.
  
  `NUMBER_BOT` - Número registrado na conta Twilio.
  
  `NUMBER_RECEIVER` - Número para enviar a mensagem no Whatsapp.
  
  `MAX_SIZE` - Tamanho máximo de arquivos a serem enviados na aplicação.
